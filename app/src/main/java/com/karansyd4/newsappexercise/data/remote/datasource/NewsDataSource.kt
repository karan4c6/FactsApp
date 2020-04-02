package com.karansyd4.newsappexercise.data.remote.datasource

import com.karansyd4.newsappexercise.data.remote.BaseDataSource
import com.karansyd4.newsappexercise.data.remote.NewsService
import javax.inject.Inject

class NewsDataSource @Inject constructor(private val newsService: NewsService) :
    BaseDataSource() {
    suspend fun fetchNews() = getResult { newsService.fetchNews() }
}
