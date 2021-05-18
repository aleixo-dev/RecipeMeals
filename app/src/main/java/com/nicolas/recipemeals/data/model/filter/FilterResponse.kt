package com.nicolas.recipemeals.data.model.filter


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class FilterResponse(
    @Json(name = "meals")
    val meals: List<Meal>
)