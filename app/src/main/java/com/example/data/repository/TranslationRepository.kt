package com.example.data.repository

import android.content.Context
import com.example.BuildConfig
import com.example.data.local.AppDatabase
import com.example.data.local.DownloadedPackEntity
import com.example.data.local.TranslationEntity
import com.example.data.model.Language
import com.example.data.offline.OfflineTranslationEngine
import com.example.data.offline.OfflineTranslationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class TranslationRepository(context: Context) {

    private val db = AppDatabase.getDatabase(context)
    private val dao = db.translationDao()

    val allHistory: Flow<List<TranslationEntity>> = dao.getAllHistory()
    val favorites: Flow<List<TranslationEntity>> = dao.getFavorites()
    val downloadedPacks: Flow<List<DownloadedPackEntity>> = dao.getDownloadedPacks()

    fun searchHistory(query: String): Flow<List<TranslationEntity>> = dao.searchHistory(query)

    suspend fun translate(
        text: String,
        sourceLangCode: String,
        targetLangCode: String,
        forceOfflineOnly: Boolean = false
    ): TranslationEntity = withContext(Dispatchers.IO) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) {
            return@withContext TranslationEntity(
                sourceText = "",
                translatedText = "",
                sourceLanguageCode = sourceLangCode,
                targetLanguageCode = targetLangCode,
                isOffline = true
            )
        }

        // Try offline engine first
        val offlineResult: OfflineTranslationResult = OfflineTranslationEngine.translate(
            sourceText = trimmed,
            sourceLang = sourceLangCode,
            targetLang = targetLangCode
        )

        var finalTranslation = offlineResult.translatedText
        var finalPhonetic = offlineResult.phonetic
        var isOfflineUsed = true

        // If not forced offline and we have a valid Gemini API key and offline was not an exact phrase match,
        // we can try online AI translation enhancement
        val apiKey = try { BuildConfig.GEMINI_API_KEY } catch (e: Exception) { "" }
        val hasValidApiKey = apiKey.isNotBlank() && apiKey != "MY_GEMINI_API_KEY"

        if (!forceOfflineOnly && hasValidApiKey && !offlineResult.isExactMatch) {
            val onlineResult = tryOnlineGeminiTranslate(
                text = trimmed,
                sourceLang = Language.findByCode(sourceLangCode).name,
                targetLang = Language.findByCode(targetLangCode).name,
                apiKey = apiKey
            )
            if (onlineResult != null && onlineResult.isNotBlank()) {
                finalTranslation = onlineResult
                isOfflineUsed = false
            }
        }

        val entity = TranslationEntity(
            sourceText = trimmed,
            translatedText = finalTranslation,
            sourceLanguageCode = sourceLangCode,
            targetLanguageCode = targetLangCode,
            isOffline = isOfflineUsed,
            phonetic = finalPhonetic,
            timestamp = System.currentTimeMillis()
        )

        val id = dao.insertTranslation(entity)
        entity.copy(id = id)
    }

    private suspend fun tryOnlineGeminiTranslate(
        text: String,
        sourceLang: String,
        targetLang: String,
        apiKey: String
    ): String? = withContext(Dispatchers.IO) {
        try {
            val urlString = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"
            val url = URL(urlString)
            val conn = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                connectTimeout = 8000
                readTimeout = 8000
                doOutput = true
                setRequestProperty("Content-Type", "application/json")
            }

            val prompt = "Translate the following text from $sourceLang to $targetLang. Output ONLY the translated text without commentary or quotes:\n\n$text"
            val jsonPayload = JSONObject().apply {
                val contents = org.json.JSONArray().apply {
                    val contentObj = JSONObject().apply {
                        val parts = org.json.JSONArray().apply {
                            put(JSONObject().put("text", prompt))
                        }
                        put("parts", parts)
                    }
                    put(contentObj)
                }
                put("contents", contents)
            }

            OutputStreamWriter(conn.outputStream).use { writer ->
                writer.write(jsonPayload.toString())
                writer.flush()
            }

            if (conn.responseCode == 200) {
                val responseText = conn.inputStream.bufferedReader().use { it.readText() }
                val rootJson = JSONObject(responseText)
                val candidates = rootJson.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val candidate = candidates.getJSONObject(0)
                    val content = candidate.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    if (parts != null && parts.length() > 0) {
                        return@withContext parts.getJSONObject(0).optString("text").trim()
                    }
                }
            }
            null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun toggleFavorite(id: Long, currentStatus: Boolean) = withContext(Dispatchers.IO) {
        dao.setFavorite(id, !currentStatus)
    }

    suspend fun deleteHistoryItem(id: Long) = withContext(Dispatchers.IO) {
        dao.deleteById(id)
    }

    suspend fun clearHistory() = withContext(Dispatchers.IO) {
        dao.clearHistoryNonFavorites()
    }

    suspend fun downloadLanguagePack(languageCode: String, sizeMb: Int) = withContext(Dispatchers.IO) {
        dao.insertDownloadedPack(DownloadedPackEntity(languageCode, System.currentTimeMillis(), sizeMb))
    }

    suspend fun deleteLanguagePack(languageCode: String) = withContext(Dispatchers.IO) {
        dao.deleteDownloadedPack(languageCode)
    }
}
