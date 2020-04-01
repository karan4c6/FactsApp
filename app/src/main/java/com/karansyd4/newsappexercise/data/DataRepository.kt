package com.karansyd4.newsappexercise.data

import android.util.Log
import com.karansyd4.newsappexercise.data.remote.datasource.RemoteDataSource
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject
constructor(
    private val remoteDataSource: RemoteDataSource
) {

    private val TAG = DataRepository::class.java.simpleName

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Log.e(TAG, "An error happened: $message")
    }
}
