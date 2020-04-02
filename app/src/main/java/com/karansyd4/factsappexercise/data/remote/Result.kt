package com.karansyd4.factsappexercise.data.remote

data class Result<out T>(
    val status: Status,
    val title: String?,
    val data: T?,
    val message: String?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(title: String, data: T): Result<T> {
            return Result(Status.SUCCESS, title, data, null)
        }

        fun <T> error(title: String, message: String, data: T? = null): Result<T> {
            return Result(Status.ERROR, title, data, message)
        }

        fun <T> loading(title: String? = null, data: T? = null): Result<T> {
            return Result(Status.LOADING, title, data, null)
        }
    }
}