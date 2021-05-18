package com.nicolas.recipemeals.data.repository

import com.nicolas.recipemeals.data.model.categories.Category
import com.nicolas.recipemeals.data.model.filter.Meal

interface ApiRepository {


    suspend fun getAllCategories(): List<Category>
    suspend fun getFilterMeals(categoryMeals: String): List<Meal>
    suspend fun getDetailMeals(idMeal: String): List<Meal>
    suspend fun getSearchMeal(searchMeal: String): List<Meal>?

}