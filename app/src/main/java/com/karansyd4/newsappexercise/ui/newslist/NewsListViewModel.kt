package com.karansyd4.newsappexercise.ui.newslist

import androidx.lifecycle.MutableLiveData
import com.karansyd4.newsappexercise.data.DataRepository
import com.karansyd4.newsappexercise.di.CoroutineScropeIO
import com.karansyd4.newsappexercise.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import java.util.*
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    @CoroutineScropeIO private val coroutineScope: CoroutineScope
) : BaseViewModel() {

    var mutableListLiveDataResult: MutableLiveData<Result<List<NewsItem>>> = MutableLiveData()
    var connectivityAvailable = false

    /**
     * Get News list from data repository
     */
    fun getNewsList() {
        dataRepository.observeNews(coroutineScope) {
            mutableListLiveDataResult.postValue(it)
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    fun searchByTitle(newsItems: List<NewsItem>, keyWord: String): NewsItem? {
        for (newsItem in newsItems) {
            if (newsItem.title.isNotEmpty() && newsItem.title.toLowerCase(Locale.ROOT).contains(keyWord.toLowerCase(Locale.ROOT))) {
                return newsItem
            }
        }
        return null
    }
}