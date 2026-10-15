package com.example

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.AppDatabase
import com.example.data.local.TranslationEntity
import com.example.data.model.Language
import com.example.data.offline.OfflineDictionaryData
import com.example.data.offline.OfflineTranslationEngine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import com.example.ui.TranslatorViewModel

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `test app name string resource`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Offline Translator", appName)
    }

    @Test
    fun `test offline translation engine exact phrasebook match`() {
        val result = OfflineTranslationEngine.translate(
            sourceText = "Hello",
            sourceLang = "en",
            targetLang = "es"
        )
        assertEquals("Hola", result.translatedText)
        assertTrue(result.isExactMatch)
    }

    @Test
    fun `test offline translation engine multi-language support`() {
        val esResult = OfflineTranslationEngine.translate("Thank you very much", "en", "es")
        assertEquals("Muchas gracias", esResult.translatedText)

        val frResult = OfflineTranslationEngine.translate("Thank you very much", "en", "fr")
        assertEquals("Merci beaucoup", frResult.translatedText)

        val jaResult = OfflineTranslationEngine.translate("Thank you very much", "en", "ja")
        assertEquals("どうもありがとうございます", jaResult.translatedText)
        assertNotNull(jaResult.phonetic)
    }

    @Test
    fun `test offline phrasebook database items count`() {
        assertTrue(OfflineDictionaryData.PHRASEBOOK_ITEMS.isNotEmpty())
        assertTrue(OfflineDictionaryData.DICTIONARY_MAP.size >= 40)
        assertTrue(Language.ALL_LANGUAGES.size >= 12)
    }

    @Test
    fun `test database operations and offline persistence`() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = AppDatabase.getDatabase(context)
        val dao = db.translationDao()

        val entity1 = TranslationEntity(
            sourceText = "Where is the train station?",
            translatedText = "¿Dónde está la estación de tren?",
            sourceLanguageCode = "en",
            targetLanguageCode = "es",
            isOffline = true
        )

        val id1 = dao.insertTranslation(entity1)
        assertTrue(id1 > 0)

        val entity2 = TranslationEntity(
            sourceText = "Good morning",
            translatedText = "Bonjour",
            sourceLanguageCode = "en",
            targetLanguageCode = "fr",
            isOffline = true
        )
        val id2 = dao.insertTranslation(entity2)
        assertTrue(id2 > 0)

        // Query all history
        val allHistory = dao.getAllHistory().first()
        assertTrue(allHistory.any { it.sourceText == "Where is the train station?" })
        assertTrue(allHistory.any { it.sourceText == "Good morning" })

        // Test search query
        val searchResults = dao.searchHistory("station").first()
        assertEquals(1, searchResults.size)
        assertEquals("Where is the train station?", searchResults[0].sourceText)

        // Test favorite toggle
        dao.setFavorite(id1, true)
        val favorites = dao.getFavorites().first()
        assertTrue(favorites.any { it.id == id1 && it.isFavorite })

        // Test single item deletion
        dao.deleteById(id2)
        val afterDelete = dao.getAllHistory().first()
        assertTrue(afterDelete.none { it.id == id2 })
    }

    @Test
    fun `test language search and lookup`() {
        val query = "spa"
        val filtered = Language.ALL_LANGUAGES.filter {
            it.name.contains(query, ignoreCase = true) ||
            it.nativeName.contains(query, ignoreCase = true) ||
            it.code.contains(query, ignoreCase = true)
        }
        assertTrue(filtered.any { it.code == "es" })

        val japaneseLookup = Language.findByCode("ja")
        assertEquals("Japanese", japaneseLookup.name)
        assertEquals("日本語", japaneseLookup.nativeName)
    }

    @Test
    fun `test in-app review manual prompt and dismissal`() {
        val app = ApplicationProvider.getApplicationContext<android.app.Application>()
        val viewModel = TranslatorViewModel(app)

        // Initially review prompt is hidden
        assertFalse(viewModel.uiState.value.showReviewPrompt)

        // Trigger manual review prompt
        viewModel.triggerReviewPromptManually()
        assertTrue(viewModel.uiState.value.showReviewPrompt)
        assertTrue(viewModel.uiState.value.translationMilestoneCount >= 1)

        // Dismiss review prompt
        viewModel.dismissReviewPrompt()
        assertFalse(viewModel.uiState.value.showReviewPrompt)

        // Submit review
        viewModel.triggerReviewPromptManually()
        assertTrue(viewModel.uiState.value.showReviewPrompt)
        viewModel.submitReview(5, "Great offline app!")
        assertFalse(viewModel.uiState.value.showReviewPrompt)
    }

    @Test
    fun `test source and target language selection and swapping`() {
        val app = ApplicationProvider.getApplicationContext<android.app.Application>()
        val viewModel = TranslatorViewModel(app)

        val french = Language.findByCode("fr")
        val japanese = Language.findByCode("ja")

        viewModel.setSourceLanguage(french)
        viewModel.setTargetLanguage(japanese)

        assertEquals("fr", viewModel.uiState.value.sourceLanguage.code)
        assertEquals("ja", viewModel.uiState.value.targetLanguage.code)

        // Swap languages
        viewModel.swapLanguages()
        assertEquals("ja", viewModel.uiState.value.sourceLanguage.code)
        assertEquals("fr", viewModel.uiState.value.targetLanguage.code)
    }

    @Test
    fun `test copy translation to system clipboard`() {
        val app = ApplicationProvider.getApplicationContext<android.app.Application>()
        val clipboard = app.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val translatedText = "Bonjour le monde"
        val clip = ClipData.newPlainText("Translation", translatedText)
        clipboard.setPrimaryClip(clip)

        assertEquals("Bonjour le monde", clipboard.primaryClip?.getItemAt(0)?.text?.toString())
    }
}

