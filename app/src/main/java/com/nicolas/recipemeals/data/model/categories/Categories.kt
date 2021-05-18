package com.nicolas.recipemeals.data.model.categories


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Categories(
    @Json(name = "categories")
    val categories: List<Category>
)