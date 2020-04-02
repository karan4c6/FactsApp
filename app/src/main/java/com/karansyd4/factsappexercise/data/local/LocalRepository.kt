package com.karansyd4.factsappexercise.data.local

import com.karansyd4.factsappexercise.data.local.dao.FactsDao
import com.karansyd4.factsappexercise.data.local.model.FactsItem
import com.karansyd4.factsappexercise.data.remote.dto.Facts
import javax.inject.Inject

class LocalRepository @Inject
constructor(private val dao: FactsDao) {

    /**
     * Insert all facts data into database
     *
     * @param factsModel from api
     */
    fun saveFacts(factsModel: List<Facts>) {
        clearDatabase()
        val factsItemList: ArrayList<FactsItem> = arrayListOf()
        for (factsItem in factsModel) {
            val factsModelItem = FactsItem()
            factsItem.title?.let {
                factsModelItem.title = it
            }
            factsItem.description?.let {
                factsModelItem.description = it
            }
            factsItem.imageHref?.let {
                factsModelItem.url = it
            }
            factsItemList.add(factsModelItem)
        }
        dao.insertAllFactsItem(factsItemList)
    }

    /**
     * Get facts data list
     */
    fun getFactsList() = dao.getFacts()

    /**
     * Get facts data list size
     */
    fun getFactsListSize() = dao.getFactsListSize()

    /**
     * Clear all data from database
     */
    private fun clearDatabase() {
        dao.deleteAllFacts()
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: LocalRepository? = null

        fun getInstance(dao: FactsDao) =
            instance ?: synchronized(this) {
                instance
                    ?: LocalRepository(dao).also { instance = it }
            }
    }
}