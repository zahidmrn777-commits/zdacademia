package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [TranslationEntity::class, DownloadedPackEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun translationDao(): TranslationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "offline_translator.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Seed default pre-installed language packs
                        CoroutineScope(Dispatchers.IO).launch {
                            val dao = getDatabase(context).translationDao()
                            dao.insertDownloadedPack(DownloadedPackEntity("en", System.currentTimeMillis(), 45))
                            dao.insertDownloadedPack(DownloadedPackEntity("es", System.currentTimeMillis(), 42))
                            dao.insertDownloadedPack(DownloadedPackEntity("fr", System.currentTimeMillis(), 40))
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
