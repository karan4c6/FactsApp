package com.karansyd4.factsappexercise.data

import android.util.Log
import com.karansyd4.factsappexercise.data.local.LocalRepository
import com.karansyd4.factsappexercise.data.local.model.FactsItem
import com.karansyd4.factsappexercise.data.remote.Result
import com.karansyd4.factsappexercise.data.remote.datasource.FactsDataSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FactsRepository @Inject
constructor(
    private val factsDataSource: FactsDataSource,
    private val localRepository: LocalRepository
) {

    private val TAG = FactsRepository::class.java.simpleName

    fun observeFacts(
        scope: CoroutineScope,
        callback: (Result<List<FactsItem>>) -> Unit
    ) {
        scope.launch(Dispatchers.IO + getJobErrorHandler()) {
            callback(Result.loading())
            val response = factsDataSource.fetchFacts()
            when (response.status) {
                Result.Status.SUCCESS -> {

                    response.data?.let {
                        Log.d(TAG, "observeFacts: ${it.title}")
                        localRepository.saveFacts(it.rows)
                    }

                    callback(Result.success(localRepository.getFactsList()))
                }
                Result.Status.ERROR -> {
                    when {
                        localRepository.getFactsListSize() > 0 -> callback(
                            Result.success(
                                localRepository.getFactsList()
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
