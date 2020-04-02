package com.karansyd4.newsappexercise.ui.newslist

import androidx.lifecycle.MutableLiveData
import com.karansyd4.newsappexercise.data.NewsRepository
import com.karansyd4.newsappexercise.data.local.model.NewsItem
import com.karansyd4.newsappexercise.di.CoroutineScropeIO
import com.karansyd4.newsappexercise.ui.base.BaseViewModel
import com.karansyd4.newsappexercise.data.remote.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    @CoroutineScropeIO private val coroutineScope: CoroutineScope
) : BaseViewModel() {

    var mutableListLiveDataResult: MutableLiveData<Result<List<NewsItem>>> = MutableLiveData()
    var connectivityAvailable = false

    /**
     * Get News list from data repository
     */
    fun getNewsList() {
        newsRepository.observeNews(coroutineScope) {
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

}