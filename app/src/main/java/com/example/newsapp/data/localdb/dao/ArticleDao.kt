package com.example.newsapp.data.localdb.dao

import androidx.room.*
import com.example.newsapp.data.localdb.model.LocalArticle
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(order: List<LocalArticle>)

    @Query("SELECT * FROM localArticle")
    fun getAll(): Flow<List<LocalArticle>>

    @Query("SELECT * FROM localArticle WHERE title LIKE '%' || :title || '%' OR description LIKE '%' || :description || '%' OR author LIKE '%' || :author || '%' OR content LIKE '%' || :content || '%'")
    fun searchArticles(
        title: String,
        description: String,
        author: String,
        content: String
    ): Flow<List<LocalArticle>>

    /**
     * I have used flows and coroutines for applying reactive programming where the wishlist and basket badges
     * are always observed form the ROOM database and thus the UI will always be updated when removing/adding items to
     * the database table.
     */

/*    @Query("SELECT COUNT(*) FROM wishlist")
    fun getReactiveWishListCount() : Flow<Int>

    @Query("SELECT COUNT(*) FROM wishlist")
    fun getWishListCount() : Int


    @Transaction
    fun deleteItemAndGetCount(wishlist : Wishlist): Int {
        deleteItem(wishlist)
       return getWishListCount()
    }*/
}