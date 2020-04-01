package com.karansyd4.newsappexercise.data.local

import com.karansyd4.newsappexercise.data.local.dao.NewsDao
import com.karansyd4.newsappexercise.data.local.model.NewsItem
import com.karansyd4.newsappexercise.data.remote.dto.Row
import javax.inject.Inject

class LocalRepository @Inject
constructor(private val dao: NewsDao) {

    /**
     * Insert all news data into database
     *
     * @param newsModel from api
     */
    fun saveNews(newsModel: List<Row>) {
        clearDatabase()
        val newsItemList: ArrayList<NewsItem> = arrayListOf()
        for (newsItem in newsModel) {
            val newsModelItem = NewsItem()
            newsItem.title?.let {
                newsModelItem.title = it
            }
            newsItem.description?.let {
                newsModelItem.description = it
            }
            newsItem.imageHref?.let {
                newsModelItem.url = it
            }
            newsItemList.add(newsModelItem)
        }
        dao.insertAllNewsItem(newsItemList)
    }

    /**
     * Get news data list
     */
    fun getNewsList() = dao.getNews()

    /**
     * Get news data list size
     */
    fun getNewsListSize() = dao.getNewsListSize()

    /**
     * Clear all data from database
     */
    private fun clearDatabase() {
        dao.deleteOldNews()
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: LocalRepository? = null

        fun getInstance(dao: NewsDao) =
            instance ?: synchronized(this) {
                instance
                    ?: LocalRepository(dao).also { instance = it }
            }
    }
}