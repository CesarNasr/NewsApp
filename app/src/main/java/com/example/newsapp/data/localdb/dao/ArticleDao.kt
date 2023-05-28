package com.example.newsapp.data.localdb.dao

import androidx.room.*
import com.example.newsapp.data.localdb.model.LocalArticle
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(order: List<LocalArticle>)

    @Query("DELETE FROM localArticle")
    fun clearAll()

    @Query("SELECT * FROM localArticle")
    fun getAll(): Flow<List<LocalArticle>>


    @Query("SELECT * FROM localArticle WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' OR author LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    fun searchArticles(
        query :String
    ): Flow<List<LocalArticle>>


    @Transaction
    fun insertAllArticles(order: List<LocalArticle>){
        clearAll()
        insertAll(order)
    }

    /**
     * I have used flows and coroutines for applying reactive programming for fetching data from room db
     * these data are always observed form the ROOM database and thus the UI will always be updated when removing/adding items to
     * the database table.
     */
}