package com.nicolas.recipemeals.data.repository.databaserepository

import com.nicolas.recipemeals.data.model.filter.Meal

interface RecipeRepository {

    suspend fun insertRecipe(meal: Meal)
    suspend fun deleteRecipe(meal: Meal)
    suspend fun getAllRecipe(): List<Meal>

}