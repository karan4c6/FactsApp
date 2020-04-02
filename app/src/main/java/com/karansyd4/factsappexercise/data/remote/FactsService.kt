package com.karansyd4.factsappexercise.data.remote

import com.karansyd4.factsappexercise.data.remote.dto.Facts
import retrofit2.Response
import retrofit2.http.GET

interface FactsService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun fetchFacts(): Response<NewsResponse<Facts>>
}