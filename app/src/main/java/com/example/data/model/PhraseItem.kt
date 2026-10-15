package com.example.data.model

import androidx.annotation.Keep

enum class PhraseCategory(val displayName: String, val iconName: String) {
    ESSENTIALS("Essentials", "Star"),
    GREETINGS("Greetings", "Waving"),
    TRAVEL("Travel & Directions", "Flight"),
    DINING("Food & Dining", "Restaurant"),
    EMERGENCY("Emergency & Medical", "LocalHospital"),
    SHOPPING("Shopping & Money", "ShoppingBag"),
    NUMBERS("Numbers & Time", "Timer"),
    CONVERSATION("Conversation", "Chat")
}

@Keep
data class PhraseItem(
    val id: String,
    val englishText: String,
    val category: PhraseCategory,
    val translations: Map<String, String>, // Language code -> translated text
    val phonetics: Map<String, String> = emptyMap() // Language code -> phonetic pronunciation guide
)
