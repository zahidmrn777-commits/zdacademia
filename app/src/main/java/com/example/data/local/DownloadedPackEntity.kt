package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded_packs")
data class DownloadedPackEntity(
    @PrimaryKey
    val languageCode: String,
    val downloadedAt: Long = System.currentTimeMillis(),
    val packSizeMb: Int
)
