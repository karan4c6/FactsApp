package com.karansyd4.newsappexercise.data.remote

import com.karansyd4.newsappexercise.data.remote.dto.Row
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun fetchNews(): Response<ResultsResponse<Row>>
}