package com.example.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "translations",
    indices = [
        Index(value = ["timestamp"]),
        Index(value = ["isFavorite"]),
        Index(value = ["sourceLanguageCode", "targetLanguageCode"])
    ]
)
data class TranslationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sourceText: String,
    val translatedText: String,
    val sourceLanguageCode: String,
    val targetLanguageCode: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false,
    val isOffline: Boolean = true,
    val phonetic: String? = null
)
