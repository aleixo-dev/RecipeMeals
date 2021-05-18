package com.nicolas.recipemeals.data.repository

import com.nicolas.recipemeals.data.api.CategoriesApiService
import com.nicolas.recipemeals.data.model.categories.Category
import com.nicolas.recipemeals.data.model.filter.Meal
import retrofit2.awaitResponse

class ApiRepositoryImpl(private val categoriesApiService: CategoriesApiService) :
    ApiRepository {

    override suspend fun getAllCategories(): List<Category> {
        val response = categoriesApiService.getAllCategories().awaitResponse()
        return response.body()!!.categories
    }

    override suspend fun getFilterMeals(categoryMeals: String): List<Meal> {
        val responseFilterMeals = categoriesApiService.getFilterMeals(categoryMeals).awaitResponse()
        return responseFilterMeals.body()!!.meals
    }

    override suspend fun getDetailMeals(idMeal: String): List<Meal> {
        val responseMealsDetails = categoriesApiService.getDetailMeals(idMeal).awaitResponse()
        return responseMealsDetails.body()!!.meals
    }

    override suspend fun getSearchMeal(searchMeal: String): List<Meal>? {
        val responseSearchMeal = categoriesApiService.getSearchMeal(searchMeal).awaitResponse()
        val body = responseSearchMeal.body()

        return if (responseSearchMeal.isSuccessful && body != null) {
            responseSearchMeal.body()!!.meals
        } else {
            null
        }
    }
}