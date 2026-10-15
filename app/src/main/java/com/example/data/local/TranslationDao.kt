package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslationDao {
    @Query("SELECT * FROM translations ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<TranslationEntity>>

    @Query("SELECT * FROM translations WHERE isFavorite = 1 ORDER BY timestamp DESC")
    fun getFavorites(): Flow<List<TranslationEntity>>

    @Query("""
        SELECT * FROM translations 
        WHERE sourceText LIKE '%' || :query || '%' 
           OR translatedText LIKE '%' || :query || '%'
        ORDER BY timestamp DESC
    """)
    fun searchHistory(query: String): Flow<List<TranslationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslation(entity: TranslationEntity): Long

    @Update
    suspend fun updateTranslation(entity: TranslationEntity)

    @Query("UPDATE translations SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(id: Long, isFavorite: Boolean)

    @Query("DELETE FROM translations WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM translations WHERE isFavorite = 0")
    suspend fun clearHistoryNonFavorites()

    @Query("DELETE FROM translations")
    suspend fun clearAll()

    // Downloaded language pack management
    @Query("SELECT * FROM downloaded_packs")
    fun getDownloadedPacks(): Flow<List<DownloadedPackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadedPack(pack: DownloadedPackEntity)

    @Query("DELETE FROM downloaded_packs WHERE languageCode = :languageCode")
    suspend fun deleteDownloadedPack(languageCode: String)

    @Query("SELECT COUNT(*) FROM downloaded_packs WHERE languageCode = :languageCode")
    suspend fun isPackDownloaded(languageCode: String): Int
}
