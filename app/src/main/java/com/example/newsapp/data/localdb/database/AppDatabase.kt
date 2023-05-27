package com.example.newsapp.data.localdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.localdb.dao.ArticleDao
import com.example.newsapp.data.localdb.model.LocalArticle

const val DB_NAME = "news.db"

@Database(
    entities = [LocalArticle::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun ArticleDao(): ArticleDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, DB_NAME)
            .build()
    }
}