package com.karansyd4.factsappexercise.data.remote

import com.google.gson.annotations.SerializedName

data class FactsResponse<T>(
    @SerializedName("rows")
    val rows: List<T>,
    @SerializedName("title")
    val title: String
)
