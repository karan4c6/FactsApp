package com.karansyd4.factsappexercise.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karansyd4.factsappexercise.data.local.dao.FactsDao
import com.karansyd4.factsappexercise.data.local.model.FactsItem

@Database(
    entities = [FactsItem::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun factsDao(): FactsDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }
            }
            return INSTANCE!!
        }

        // Create and pre-populate the database.
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "facts-db")
                .build()
        }
    }
}