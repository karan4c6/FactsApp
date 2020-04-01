package com.karansyd4.newsappexercise.data.local.model

import androidx.room.*

@Entity(tableName = "news")
data class NewsItem(
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