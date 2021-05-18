package com.nicolas.recipemeals.data.model

import java.io.Serializable

/**
 * @Alteracao:
 * -> TODO: Remover essa classe, pois esse toEntity deve ser criado na classe Meal!
 */

data class Recipe(
    val idMeal: Int?,
    val strMeal: String?,
    val strMealThumb: String?,
    val strInstructions: String?,
    val strArea: String?,
    val strCategory: String?,
) : Serializable
