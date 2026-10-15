package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.TranslationEntity
import com.example.data.model.Language
import com.example.data.model.PhraseCategory
import com.example.data.model.PhraseItem
import com.example.data.offline.OfflineDictionaryData
import com.example.data.repository.TranslationRepository
import com.example.service.TextToSpeechManager
import com.example.service.VoiceInputState
import com.example.service.VoiceSpeechManager
import com.example.ui.theme.AppThemeMode
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ConversationMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val speakerLangCode: String,
    val originalText: String,
    val translatedText: String,
    val phonetic: String? = null,
    val isLeftSpeaker: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

data class TranslatorUiState(
    val sourceLanguage: Language = Language.findByCode("en"),
    val targetLanguage: Language = Language.findByCode("es"),
    val inputText: String = "",
    val currentTranslation: TranslationEntity? = null,
    val isTranslating: Boolean = false,
    val isOfflineOnly: Boolean = true,
    val themeMode: AppThemeMode = AppThemeMode.DARK,
    val historySearchQuery: String = "",
    val phraseSearchQuery: String = "",
    val selectedPhraseCategory: PhraseCategory = PhraseCategory.ESSENTIALS,
    val downloadedPackCodes: Set<String> = setOf("en", "es", "fr"),
    val downloadingPacks: Map<String, Float> = emptyMap(),
    val conversationMessages: List<ConversationMessage> = emptyList(),
    val activeConversationSpeaker: String? = null, // "source" or "target"
    val showFullscreenCard: Boolean = false,
    val showReviewPrompt: Boolean = false,
    val translationMilestoneCount: Int = 0
)

class TranslatorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TranslationRepository(application)
    private val prefs = application.getSharedPreferences("app_review_prefs", android.content.Context.MODE_PRIVATE)

    val voiceSpeechManager = VoiceSpeechManager(application)
    val textToSpeechManager = TextToSpeechManager(application)

    companion object {
        private const val PREF_TRANSLATION_COUNT = "key_translation_count"
        private const val PREF_HAS_REVIEWED = "key_has_reviewed"
        private const val PREF_DECLINED_REVIEW = "key_declined_review"
        private const val PREF_LAST_PROMPT_COUNT = "key_last_prompt_count"
        private const val INITIAL_REVIEW_THRESHOLD = 3
        private const val REPEAT_INTERVAL = 5
    }

    private val _uiState = MutableStateFlow(TranslatorUiState())
    val uiState: StateFlow<TranslatorUiState> = _uiState.asStateFlow()

    private var autoTranslateJob: Job? = null

    // History and favorites flows
    val historyList: StateFlow<List<TranslationEntity>> = repository.allHistory
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoriteList: StateFlow<List<TranslationEntity>> = repository.favorites
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        // Collect downloaded packs
        viewModelScope.launch {
            repository.downloadedPacks.collect { packs ->
                val codes = packs.map { it.languageCode }.toSet()
                _uiState.value = _uiState.value.copy(
                    downloadedPackCodes = if (codes.isEmpty()) setOf("en", "es", "fr") else codes
                )
            }
        }
    }

    fun onInputTextChanged(newText: String) {
        _uiState.value = _uiState.value.copy(inputText = newText)
        autoTranslateJob?.cancel()

        if (newText.trim().isEmpty()) {
            _uiState.value = _uiState.value.copy(currentTranslation = null, isTranslating = false)
            return
        }

        // Debounce instant translation
        autoTranslateJob = viewModelScope.launch {
            delay(350)
            executeTranslation(newText)
        }
    }

    fun executeTranslation(text: String = _uiState.value.inputText) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isTranslating = true)
            try {
                val state = _uiState.value
                val result = repository.translate(
                    text = trimmed,
                    sourceLangCode = state.sourceLanguage.code,
                    targetLangCode = state.targetLanguage.code,
                    forceOfflineOnly = state.isOfflineOnly
                )
                _uiState.value = _uiState.value.copy(
                    currentTranslation = result,
                    isTranslating = false
                )
                if (result.translatedText.isNotBlank()) {
                    checkAndTriggerReviewPrompt()
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isTranslating = false)
            }
        }
    }

    fun setSourceLanguage(language: Language) {
        if (language.code == _uiState.value.targetLanguage.code) {
            // Swap if selecting same
            swapLanguages()
            return
        }
        _uiState.value = _uiState.value.copy(sourceLanguage = language)
        if (_uiState.value.inputText.isNotBlank()) {
            executeTranslation()
        }
    }

    fun setTargetLanguage(language: Language) {
        if (language.code == _uiState.value.sourceLanguage.code) {
            // Swap if selecting same
            swapLanguages()
            return
        }
        _uiState.value = _uiState.value.copy(targetLanguage = language)
        if (_uiState.value.inputText.isNotBlank()) {
            executeTranslation()
        }
    }

    fun swapLanguages() {
        val currentSource = _uiState.value.sourceLanguage
        val currentTarget = _uiState.value.targetLanguage
        val currentResult = _uiState.value.currentTranslation

        val newInputText = currentResult?.translatedText ?: ""

        _uiState.value = _uiState.value.copy(
            sourceLanguage = currentTarget,
            targetLanguage = currentSource,
            inputText = newInputText
        )

        if (newInputText.isNotBlank()) {
            executeTranslation(newInputText)
        }
    }

    fun clearInput() {
        autoTranslateJob?.cancel()
        _uiState.value = _uiState.value.copy(inputText = "", currentTranslation = null)
    }

    fun setOfflineOnly(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(isOfflineOnly = enabled)
        if (_uiState.value.inputText.isNotBlank()) {
            executeTranslation()
        }
    }

    fun setThemeMode(mode: AppThemeMode) {
        _uiState.value = _uiState.value.copy(themeMode = mode)
    }

    fun toggleFavorite(id: Long, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleFavorite(id, currentStatus)
            if (_uiState.value.currentTranslation?.id == id) {
                _uiState.value = _uiState.value.copy(
                    currentTranslation = _uiState.value.currentTranslation?.copy(isFavorite = !currentStatus)
                )
            }
        }
    }

    fun deleteHistoryItem(id: Long) {
        viewModelScope.launch {
            repository.deleteHistoryItem(id)
            if (_uiState.value.currentTranslation?.id == id) {
                _uiState.value = _uiState.value.copy(currentTranslation = null)
            }
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearHistory()
        }
    }

    fun selectHistoryItem(item: TranslationEntity) {
        val srcLang = Language.findByCode(item.sourceLanguageCode)
        val tgtLang = Language.findByCode(item.targetLanguageCode)
        _uiState.value = _uiState.value.copy(
            sourceLanguage = srcLang,
            targetLanguage = tgtLang,
            inputText = item.sourceText,
            currentTranslation = item
        )
    }

    fun selectPhrase(phrase: PhraseItem) {
        val srcLang = _uiState.value.sourceLanguage.code
        val tgtLang = _uiState.value.targetLanguage.code

        val srcText = phrase.translations[srcLang] ?: phrase.englishText
        val tgtText = phrase.translations[tgtLang] ?: phrase.englishText
        val phonetic = phrase.phonetics[tgtLang]

        _uiState.value = _uiState.value.copy(
            inputText = srcText,
            currentTranslation = TranslationEntity(
                sourceText = srcText,
                translatedText = tgtText,
                sourceLanguageCode = srcLang,
                targetLanguageCode = tgtLang,
                isOffline = true,
                phonetic = phonetic
            )
        )
    }

    fun setSelectedPhraseCategory(category: PhraseCategory) {
        _uiState.value = _uiState.value.copy(selectedPhraseCategory = category)
    }

    fun setPhraseSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(phraseSearchQuery = query)
    }

    fun setHistorySearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(historySearchQuery = query)
    }

    fun setShowFullscreenCard(show: Boolean) {
        _uiState.value = _uiState.value.copy(showFullscreenCard = show)
    }

    // TTS playback
    fun speakSource() {
        val text = _uiState.value.inputText
        if (text.isNotBlank()) {
            val lang = _uiState.value.sourceLanguage
            textToSpeechManager.speak(text, lang.ttsLanguage, lang.ttsCountry)
        }
    }

    fun speakTarget(customText: String? = null) {
        val text = customText ?: _uiState.value.currentTranslation?.translatedText ?: return
        if (text.isNotBlank()) {
            val lang = _uiState.value.targetLanguage
            textToSpeechManager.speak(text, lang.ttsLanguage, lang.ttsCountry)
        }
    }

    fun speakText(text: String, languageCode: String) {
        val lang = Language.findByCode(languageCode)
        textToSpeechManager.speak(text, lang.ttsLanguage, lang.ttsCountry)
    }

    // Voice recognition handlers
    fun startVoiceInput(isSource: Boolean = true) {
        val lang = if (isSource) _uiState.value.sourceLanguage else _uiState.value.targetLanguage
        voiceSpeechManager.startListening(lang.speechCode) { recognizedText ->
            if (isSource) {
                _uiState.value = _uiState.value.copy(inputText = recognizedText)
                executeTranslation(recognizedText)
            } else {
                // Translated reverse
                swapLanguages()
                _uiState.value = _uiState.value.copy(inputText = recognizedText)
                executeTranslation(recognizedText)
            }
        }
    }

    fun stopVoiceInput() {
        voiceSpeechManager.stopListening()
    }

    // Conversation mode
    fun startConversationSpeech(isLeftSpeaker: Boolean) {
        val speakerLang = if (isLeftSpeaker) _uiState.value.sourceLanguage else _uiState.value.targetLanguage
        val targetLang = if (isLeftSpeaker) _uiState.value.targetLanguage else _uiState.value.sourceLanguage

        _uiState.value = _uiState.value.copy(activeConversationSpeaker = if (isLeftSpeaker) "left" else "right")

        voiceSpeechManager.startListening(speakerLang.speechCode) { spokenText ->
            _uiState.value = _uiState.value.copy(activeConversationSpeaker = null)
            if (spokenText.isNotBlank()) {
                viewModelScope.launch {
                    val result = repository.translate(
                        text = spokenText,
                        sourceLangCode = speakerLang.code,
                        targetLangCode = targetLang.code,
                        forceOfflineOnly = _uiState.value.isOfflineOnly
                    )
                    val msg = ConversationMessage(
                        speakerLangCode = speakerLang.code,
                        originalText = spokenText,
                        translatedText = result.translatedText,
                        phonetic = result.phonetic,
                        isLeftSpeaker = isLeftSpeaker
                    )
                    _uiState.value = _uiState.value.copy(
                        conversationMessages = _uiState.value.conversationMessages + msg
                    )
                    // Auto speak translation in conversation mode
                    speakText(result.translatedText, targetLang.code)
                }
            }
        }
    }

    fun clearConversation() {
        _uiState.value = _uiState.value.copy(conversationMessages = emptyList(), activeConversationSpeaker = null)
    }

    // Language pack downloads
    fun downloadLanguagePack(lang: Language) {
        viewModelScope.launch {
            val downloading = _uiState.value.downloadingPacks.toMutableMap()
            downloading[lang.code] = 0.1f
            _uiState.value = _uiState.value.copy(downloadingPacks = downloading)

            // Simulate realistic pack download progress
            for (step in 1..10) {
                delay(120)
                val current = _uiState.value.downloadingPacks.toMutableMap()
                current[lang.code] = step / 10f
                _uiState.value = _uiState.value.copy(downloadingPacks = current)
            }

            repository.downloadLanguagePack(lang.code, lang.packSizeMb)
            val updated = _uiState.value.downloadingPacks.toMutableMap()
            updated.remove(lang.code)
            _uiState.value = _uiState.value.copy(
                downloadingPacks = updated,
                downloadedPackCodes = _uiState.value.downloadedPackCodes + lang.code
            )
        }
    }

    fun removeLanguagePack(lang: Language) {
        if (lang.code == "en") return // Keep base English
        viewModelScope.launch {
            repository.deleteLanguagePack(lang.code)
            _uiState.value = _uiState.value.copy(
                downloadedPackCodes = _uiState.value.downloadedPackCodes - lang.code
            )
        }
    }

    // In-App Review & Feedback logic
    private fun checkAndTriggerReviewPrompt() {
        val count = prefs.getInt(PREF_TRANSLATION_COUNT, 0) + 1
        prefs.edit().putInt(PREF_TRANSLATION_COUNT, count).apply()

        val hasReviewed = prefs.getBoolean(PREF_HAS_REVIEWED, false)
        val declined = prefs.getBoolean(PREF_DECLINED_REVIEW, false)
        val lastPromptCount = prefs.getInt(PREF_LAST_PROMPT_COUNT, 0)

        if (!hasReviewed && !declined) {
            val isInitialMilestone = (count == INITIAL_REVIEW_THRESHOLD)
            val isRecurringMilestone = (count > INITIAL_REVIEW_THRESHOLD && (count - lastPromptCount) >= REPEAT_INTERVAL)

            if (isInitialMilestone || isRecurringMilestone) {
                prefs.edit().putInt(PREF_LAST_PROMPT_COUNT, count).apply()
                _uiState.value = _uiState.value.copy(
                    showReviewPrompt = true,
                    translationMilestoneCount = count
                )
            }
        }
    }

    fun dismissReviewPrompt() {
        _uiState.value = _uiState.value.copy(showReviewPrompt = false)
    }

    fun submitReview(rating: Int, feedback: String) {
        prefs.edit().putBoolean(PREF_HAS_REVIEWED, true).apply()
        _uiState.value = _uiState.value.copy(showReviewPrompt = false)
    }

    fun declineReviewPermanently() {
        prefs.edit().putBoolean(PREF_DECLINED_REVIEW, true).apply()
        _uiState.value = _uiState.value.copy(showReviewPrompt = false)
    }

    fun triggerReviewPromptManually() {
        val count = prefs.getInt(PREF_TRANSLATION_COUNT, 3)
        _uiState.value = _uiState.value.copy(
            showReviewPrompt = true,
            translationMilestoneCount = if (count > 0) count else 3
        )
    }

    override fun onCleared() {
        super.onCleared()
        voiceSpeechManager.stopListening()
        textToSpeechManager.shutdown()
    }
}
