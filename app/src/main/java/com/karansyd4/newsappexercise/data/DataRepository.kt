package com.karansyd4.newsappexercise.data

import android.util.Log
import com.karansyd4.newsappexercise.data.local.LocalRepository
import com.karansyd4.newsappexercise.data.local.model.NewsItem
import com.karansyd4.newsappexercise.data.remote.Result
import com.karansyd4.newsappexercise.data.remote.datasource.RemoteDataSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject
constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localRepository: LocalRepository
) {

    private val TAG = DataRepository::class.java.simpleName

    fun observeNews(
        scope: CoroutineScope,
        callback: (Result<List<NewsItem>>) -> Unit
    ) {
        scope.launch(Dispatchers.IO + getJobErrorHandler()) {
            callback(Result.loading())
            val response = remoteDataSource.fetchNews()
            when (response.status) {
                Result.Status.SUCCESS -> {

                    response.data?.let {
                        Log.d(TAG, "observeNews: ${it.title}")
                        localRepository.saveNews(it.rows)
                    }

                    callback(Result.success(localRepository.getNewsList()))
                }
                Result.Status.ERROR -> {
                    when {
                        localRepository.getNewsListSize() > 0 -> callback(
                            Result.success(
                                localRepository.getNewsList()
                            )
                        )
                        else -> {
                            response.message?.let {
                                callback(Result.error(it))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Log.e(TAG, "An error happened: $message")
    }
}
