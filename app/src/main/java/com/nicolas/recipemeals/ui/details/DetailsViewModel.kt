package com.nicolas.recipemeals.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.data.repository.ApiRepositoryImpl
import com.nicolas.recipemeals.data.repository.databaserepository.RecipeRepositoryDaoImpl
import com.nicolas.recipemeals.utils.Resource
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val apiRepositoryImpl: ApiRepositoryImpl,
    private val recipeRepositoryDaoImpl: RecipeRepositoryDaoImpl
) : ViewModel() {

    private val _detailsMeals = MutableLiveData<Resource<List<Meal>>>()
    val detailsMeals: LiveData<Resource<List<Meal>>>
        get() = _detailsMeals

    private val _isRecipeMyList = MutableLiveData<Boolean>()
    val isRecipeMyList: LiveData<Boolean>
        get() = _isRecipeMyList

    fun insertRecipeMyList(meal: Meal) {
        viewModelScope.launch {
            recipeRepositoryDaoImpl.insertRecipe(meal)
        }
    }

    fun removeRecipeMyList(meal: Meal) {
        viewModelScope.launch {
            recipeRepositoryDaoImpl.deleteRecipe(meal)
        }
    }

    fun isRecipeMyList(meal: Meal) {
        viewModelScope.launch {
            _isRecipeMyList.value = recipeRepositoryDaoImpl.getAllRecipe().contains(meal)
        }
    }

    fun fetchDetailsMeals(idMeal: String) {
        viewModelScope.launch {
            _detailsMeals.postValue(Resource.loading(null))
            try {
                _detailsMeals.postValue(Resource.success(apiRepositoryImpl.getDetailMeals(idMeal)))
            } catch (e: Exception) {
                _detailsMeals.postValue(Resource.error("Error", null))
            }
        }
    }
}