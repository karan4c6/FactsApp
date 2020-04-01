package com.karansyd4.newsappexercise.data.local.dao

import androidx.room.*
import com.karansyd4.newsappexercise.data.local.model.NewsItem

@Dao
interface NewsDao {
    /**
     * Get all news item
     * @return List of NewsEntity
     */
    @Transaction
    @Query("SELECT * FROM news ORDER BY id")
    fun getNews(): List<NewsItem>

    /**
     * Get news item by ID
     * @return NewsEntity
     */
    @Transaction
    @Query("SELECT * FROM news WHERE id =:newId")
    fun getNewsById(newId: Long): NewsItem

    /**
     *Get all news item
     * @return List of NewsEntity
     */
    @Transaction
    @Query("SELECT Count(*) FROM news")
    fun getNewsListSize(): Int

    /**
     * Insert all NewsItem
     * @param newsItemList inserted into news database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNewsItem(newsItemList: List<NewsItem>)

    /**
     * Delete all old News data
     */
    @Query("DELETE FROM news")
    fun deleteOldNews()
}