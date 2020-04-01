package com.karansyd4.newsappexercise.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Defined all the variables as @var, because it might happen that backend send some values as null
 */
data class Row(
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("imageHref")
    var imageHref: String? = null,
    @SerializedName("title")
    var title: String? = null
)