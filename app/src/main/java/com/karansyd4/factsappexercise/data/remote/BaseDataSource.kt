package com.karansyd4.factsappexercise.data.remote

import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            when {
                response.isSuccessful -> {
                    val body = response.body()
                    when {
                        body != null -> return Result.success("", body)
                    }
                }
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error("No Internet Connection")
        }
    }

    private fun <T> error(message: String): Result<T> {
        return Result.error("", message)
    }
}