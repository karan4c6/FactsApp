package com.karansyd4.factsappexercise.data.remote.datasource

import com.karansyd4.factsappexercise.data.remote.BaseDataSource
import com.karansyd4.factsappexercise.data.remote.FactsService
import javax.inject.Inject

class FactsDataSource @Inject constructor(private val factsService: FactsService) :
    BaseDataSource() {
    suspend fun fetchFacts() = getResult { factsService.fetchFacts() }
}
