package com.example.data.offline

import java.util.Locale

data class OfflineTranslationResult(
    val translatedText: String,
    val phonetic: String? = null,
    val isExactMatch: Boolean = false,
    val confidence: Float = 1.0f,
    val matchSource: String = "Offline Pack"
)

object OfflineTranslationEngine {

    fun translate(
        sourceText: String,
        sourceLang: String,
        targetLang: String
    ): OfflineTranslationResult {
        val trimmed = sourceText.trim()
        if (trimmed.isEmpty()) {
            return OfflineTranslationResult("", null, isExactMatch = true, confidence = 1.0f)
        }

        if (sourceLang.equals(targetLang, ignoreCase = true)) {
            return OfflineTranslationResult(trimmed, null, isExactMatch = true, confidence = 1.0f)
        }

        val normalizedSource = normalize(trimmed)

        // 1. Try exact match in PHRASEBOOK_ITEMS
        for (phrase in OfflineDictionaryData.PHRASEBOOK_ITEMS) {
            val srcTranslation = phrase.translations[sourceLang]
            val tgtTranslation = phrase.translations[targetLang]

            if (srcTranslation != null && tgtTranslation != null) {
                if (normalize(srcTranslation).equals(normalizedSource, ignoreCase = true) ||
                    normalize(phrase.englishText).equals(normalizedSource, ignoreCase = true)
                ) {
                    val phonetic = phrase.phonetics[targetLang]
                    return OfflineTranslationResult(
                        translatedText = tgtTranslation,
                        phonetic = phonetic,
                        isExactMatch = true,
                        confidence = 1.0f,
                        matchSource = "Phrasebook Verified"
                    )
                }
            }
        }

        // 2. Try exact match in DICTIONARY_MAP
        val dictEntry = OfflineDictionaryData.DICTIONARY_MAP[normalizedSource]
        if (dictEntry != null && dictEntry.containsKey(targetLang)) {
            val result = dictEntry[targetLang]!!
            val capitalizedResult = if (trimmed.firstOrNull()?.isUpperCase() == true) {
                result.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            } else {
                result
            }
            return OfflineTranslationResult(
                translatedText = capitalizedResult,
                phonetic = getPhoneticHint(result, targetLang),
                isExactMatch = true,
                confidence = 0.98f,
                matchSource = "Offline Dictionary"
            )
        }

        // 3. Try reverse dictionary lookup (source text is in non-English language)
        val reverseMatch = OfflineDictionaryData.REVERSE_LOOKUP_MAP[normalizedSource]
        if (reverseMatch != null) {
            val (_, translations) = reverseMatch
            val tgtText = translations[targetLang]
            if (tgtText != null) {
                val capitalizedResult = if (trimmed.firstOrNull()?.isUpperCase() == true) {
                    tgtText.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                } else {
                    tgtText
                }
                return OfflineTranslationResult(
                    translatedText = capitalizedResult,
                    phonetic = getPhoneticHint(tgtText, targetLang),
                    isExactMatch = true,
                    confidence = 0.95f,
                    matchSource = "Offline Dictionary"
                )
            }
        }

        // 4. Subphrase and Tokenized sentence translation
        return translateSentence(trimmed, sourceLang, targetLang)
    }

    private fun translateSentence(
        input: String,
        sourceLang: String,
        targetLang: String
    ): OfflineTranslationResult {
        // Tokenize while preserving punctuation
        val tokens = input.split(Regex("(?<=\\s)|(?=\\s)|(?<=[.,!?;:])|(?=[.,!?;:])"))
        val translatedTokens = mutableListOf<String>()
        var matchCount = 0
        var totalWords = 0

        for (token in tokens) {
            if (token.isBlank() || token.matches(Regex("[.,!?;:]+"))) {
                translatedTokens.add(token)
                continue
            }

            totalWords++
            val normToken = normalize(token)

            // Direct dictionary lookup
            var translatedWord: String? = null

            // If source is English
            if (sourceLang.equals("en", ignoreCase = true)) {
                translatedWord = OfflineDictionaryData.DICTIONARY_MAP[normToken]?.get(targetLang)
            } else {
                // Check reverse lookup
                val rev = OfflineDictionaryData.REVERSE_LOOKUP_MAP[normToken]
                if (rev != null && rev.first.equals(sourceLang, ignoreCase = true)) {
                    translatedWord = rev.second[targetLang]
                } else if (rev != null) {
                    translatedWord = rev.second[targetLang]
                }
            }

            if (translatedWord != null) {
                matchCount++
                val wordWithCase = if (token.firstOrNull()?.isUpperCase() == true) {
                    translatedWord.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                } else {
                    translatedWord
                }
                translatedTokens.add(wordWithCase)
            } else {
                // Keep original word
                translatedTokens.add(token)
            }
        }

        val resultString = translatedTokens.joinToString("")
        val confidence = if (totalWords > 0) (matchCount.toFloat() / totalWords.toFloat()).coerceIn(0.4f, 0.92f) else 0.5f

        return OfflineTranslationResult(
            translatedText = resultString,
            phonetic = getPhoneticHint(resultString, targetLang),
            isExactMatch = false,
            confidence = confidence,
            matchSource = if (matchCount > 0) "Offline Token Match" else "Offline Direct Output"
        )
    }

    private fun normalize(text: String): String {
        return text.trim()
            .lowercase(Locale.ROOT)
            .replace(Regex("[.,!?;:¿¡\"]"), "")
            .trim()
    }

    private fun getPhoneticHint(text: String, targetLang: String): String? {
        // Quick phonetic transliteration hints for Asian / Arabic / Cyrillic scripts
        return when (targetLang.lowercase()) {
            "ja" -> when (text) {
                "こんにちは" -> "Konnichiwa"
                "ありがとう" -> "Arigatō"
                "水" -> "Mizu"
                "はい" -> "Hai"
                "いいえ" -> "Iie"
                "駅" -> "Eki"
                "空港" -> "Kūkō"
                "ホテル" -> "Hoteru"
                "美味しい" -> "Oishii"
                else -> null
            }
            "zh" -> when (text) {
                "你好" -> "Nǐ hǎo"
                "谢谢" -> "Xièxiè"
                "水" -> "Shuǐ"
                "是" -> "Shì"
                "不" -> "Bù"
                "再见" -> "Zàijiàn"
                else -> null
            }
            "ko" -> when (text) {
                "안녕하세요" -> "Annyeonghaseyo"
                "감사합니다" -> "Gamsahamnida"
                "물" -> "Mul"
                "네" -> "Ne"
                "아니요" -> "Aniyo"
                else -> null
            }
            "ru" -> when (text) {
                "Здравствуйте" -> "Zdravstvuyte"
                "Спасибо" -> "Spasibo"
                "вода" -> "Voda"
                "да" -> "Da"
                "нет" -> "Net"
                else -> null
            }
            "ar" -> when (text) {
                "مرحبا" -> "Marhaban"
                "شكرا" -> "Shukran"
                "ماء" -> "Ma'a"
                "نعم" -> "Na'am"
                "لا" -> "La"
                else -> null
            }
            "hi" -> when (text) {
                "नमस्ते" -> "Namaste"
                "धन्यवाद" -> "Dhanyavaad"
                "पानी" -> "Paani"
                "हाँ" -> "Haan"
                "नहीं" -> "Nahi"
                else -> null
            }
            else -> null
        }
    }
}
