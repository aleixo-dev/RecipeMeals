package com.nicolas.recipemeals.data.api

import com.nicolas.recipemeals.data.model.categories.Categories
import com.nicolas.recipemeals.data.model.filter.FilterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesApiService {

    @GET("categories.php")
    fun getAllCategories(): Call<Categories>

    @GET("filter.php")
    fun getFilterMeals(@Query("c") nameMeals: String): Call<FilterResponse>

    @GET("lookup.php")
    fun getDetailMeals(@Query("i") idMeal: String): Call<FilterResponse>

    @GET("search.php")
    fun getSearchMeal(@Query("s") searchMeal : String) : Call<FilterResponse>

}