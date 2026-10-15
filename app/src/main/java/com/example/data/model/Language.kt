package com.example.data.model

import androidx.annotation.Keep

@Keep
data class Language(
    val code: String,
    val name: String,
    val nativeName: String,
    val flagEmoji: String,
    val speechCode: String,
    val ttsLanguage: String,
    val ttsCountry: String = "",
    val packSizeMb: Int = 38,
    val isDownloaded: Boolean = false
) {
    companion object {
        val ALL_LANGUAGES = listOf(
            Language(
                code = "en",
                name = "English",
                nativeName = "English",
                flagEmoji = "🇺🇸",
                speechCode = "en-US",
                ttsLanguage = "en",
                ttsCountry = "US",
                packSizeMb = 45,
                isDownloaded = true
            ),
            Language(
                code = "es",
                name = "Spanish",
                nativeName = "Español",
                flagEmoji = "🇪🇸",
                speechCode = "es-ES",
                ttsLanguage = "es",
                ttsCountry = "ES",
                packSizeMb = 42,
                isDownloaded = true
            ),
            Language(
                code = "fr",
                name = "French",
                nativeName = "Français",
                flagEmoji = "🇫🇷",
                speechCode = "fr-FR",
                ttsLanguage = "fr",
                ttsCountry = "FR",
                packSizeMb = 40,
                isDownloaded = true
            ),
            Language(
                code = "de",
                name = "German",
                nativeName = "Deutsch",
                flagEmoji = "🇩🇪",
                speechCode = "de-DE",
                ttsLanguage = "de",
                ttsCountry = "DE",
                packSizeMb = 44,
                isDownloaded = false
            ),
            Language(
                code = "it",
                name = "Italian",
                nativeName = "Italiano",
                flagEmoji = "🇮🇹",
                speechCode = "it-IT",
                ttsLanguage = "it",
                ttsCountry = "IT",
                packSizeMb = 36,
                isDownloaded = false
            ),
            Language(
                code = "pt",
                name = "Portuguese",
                nativeName = "Português",
                flagEmoji = "🇵🇹",
                speechCode = "pt-PT",
                ttsLanguage = "pt",
                ttsCountry = "PT",
                packSizeMb = 39,
                isDownloaded = false
            ),
            Language(
                code = "ja",
                name = "Japanese",
                nativeName = "日本語",
                flagEmoji = "🇯🇵",
                speechCode = "ja-JP",
                ttsLanguage = "ja",
                ttsCountry = "JP",
                packSizeMb = 55,
                isDownloaded = false
            ),
            Language(
                code = "zh",
                name = "Chinese (Simplified)",
                nativeName = "简体中文",
                flagEmoji = "🇨🇳",
                speechCode = "zh-CN",
                ttsLanguage = "zh",
                ttsCountry = "CN",
                packSizeMb = 60,
                isDownloaded = false
            ),
            Language(
                code = "ko",
                name = "Korean",
                nativeName = "한국어",
                flagEmoji = "🇰🇷",
                speechCode = "ko-KR",
                ttsLanguage = "ko",
                ttsCountry = "KR",
                packSizeMb = 48,
                isDownloaded = false
            ),
            Language(
                code = "ru",
                name = "Russian",
                nativeName = "Русский",
                flagEmoji = "🇷🇺",
                speechCode = "ru-RU",
                ttsLanguage = "ru",
                ttsCountry = "RU",
                packSizeMb = 47,
                isDownloaded = false
            ),
            Language(
                code = "ar",
                name = "Arabic",
                nativeName = "العربية",
                flagEmoji = "🇸🇦",
                speechCode = "ar-SA",
                ttsLanguage = "ar",
                ttsCountry = "SA",
                packSizeMb = 52,
                isDownloaded = false
            ),
            Language(
                code = "hi",
                name = "Hindi",
                nativeName = "हिन्दी",
                flagEmoji = "🇮🇳",
                speechCode = "hi-IN",
                ttsLanguage = "hi",
                ttsCountry = "IN",
                packSizeMb = 46,
                isDownloaded = false
            )
        )

        fun findByCode(code: String): Language {
            return ALL_LANGUAGES.find { it.code.equals(code, ignoreCase = true) }
                ?: ALL_LANGUAGES.first()
        }
    }
}
