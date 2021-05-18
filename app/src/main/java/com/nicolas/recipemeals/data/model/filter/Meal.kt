package com.nicolas.recipemeals.data.model.filter


import com.nicolas.recipemeals.data.db.entity.MealEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

data class Meal(
    @Json(name = "idMeal")
    val idMeal: String?,
    @Json(name = "strMeal")
    val strMeal: String?,
    @Json(name = "strMealThumb")
    val strMealThumb: String?,
    @Json(name = "strInstructions")
    val strInstructions: String?,
    @Json(name = "strArea")
    val strArea: String?,
    @Json(name = "strCategory")
    val strCategory: String?,
) : Serializable

/**
 * @Solucao: TODO: Criar um toEntity aqui!
 * */

fun toEntity(meal: Meal): MealEntity {
    return MealEntity(
        idMeal = meal.idMeal?.toInt(),
        strMeal = meal.strMeal,
        strMealThumb = meal.strMealThumb,
        strInstructions = meal.strInstructions,
        strArea = meal.strArea,
        strCategory = meal.strCategory
    )
}