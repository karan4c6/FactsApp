package com.karansyd4.newsappexercise.data.remote

import com.google.gson.annotations.SerializedName

data class NewsResponse<T>(
    @SerializedName("rows")
    val rows: List<T>,
    @SerializedName("title")
    val title: String
)
