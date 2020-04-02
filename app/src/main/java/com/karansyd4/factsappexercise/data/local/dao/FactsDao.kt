package com.karansyd4.factsappexercise.data.local.dao

import androidx.room.*
import com.karansyd4.factsappexercise.data.local.model.FactsItem

@Dao
interface FactsDao {
    /**
     * Get all facts item
     * @return List of FactsEntity
     */
    @Transaction
    @Query("SELECT * FROM facts ORDER BY id")
    fun getFacts(): List<FactsItem>

    /**
     * Get facts item by ID
     * @return FactEntity
     */
    @Transaction
    @Query("SELECT * FROM facts WHERE id =:factId")
    fun getFactsById(factId: Long): FactsItem

    /**
     *Get all facts item
     * @return List of FactsEntity
     */
    @Transaction
    @Query("SELECT Count(*) FROM facts")
    fun getFactsListSize(): Int

    /**
     * Insert all FactsItem
     * @param factsItemList inserted into facts database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFactsItem(factsItemList: List<FactsItem>)

    /**
     * Delete all old Facts data
     */
    @Query("DELETE FROM facts")
    fun deleteAllFacts()
}