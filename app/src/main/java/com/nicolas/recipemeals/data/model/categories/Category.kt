package com.nicolas.recipemeals.data.model.categories


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

data class Category(
    @Json(name = "idCategory")
    val idCategory: String,
    @Json(name = "strCategory")
    val strCategory: String,
    @Json(name = "strCategoryDescription")
    val strCategoryDescription: String,
    @Json(name = "strCategoryThumb")
    val strCategoryThumb: String
) : Serializable