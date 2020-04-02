package com.karansyd4.factsappexercise.data.local.model

import androidx.room.*

@Entity(tableName = "facts")
data class FactsItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var title: String,

    var description: String,

    var url: String
) {
    constructor() : this(
        0, "", "", ""
    )
}