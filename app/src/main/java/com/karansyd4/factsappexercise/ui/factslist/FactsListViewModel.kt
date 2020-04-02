package com.karansyd4.factsappexercise.ui.factslist

import androidx.lifecycle.MutableLiveData
import com.karansyd4.factsappexercise.data.FactsRepository
import com.karansyd4.factsappexercise.data.local.model.FactsItem
import com.karansyd4.factsappexercise.data.remote.Result
import com.karansyd4.factsappexercise.di.CoroutineScropeIO
import com.karansyd4.factsappexercise.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class FactsListViewModel @Inject constructor(
    private val factsRepository: FactsRepository,
    @CoroutineScropeIO private val coroutineScope: CoroutineScope
) : BaseViewModel() {

    var mutableListLiveDataResult: MutableLiveData<Result<List<FactsItem>>> = MutableLiveData()
    var connectivityAvailable = false

    /**
     * Get Facts list from data repository
     */
    fun getFactsList() {
        factsRepository.observeFacts(coroutineScope) {
            mutableListLiveDataResult.postValue(it)
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}