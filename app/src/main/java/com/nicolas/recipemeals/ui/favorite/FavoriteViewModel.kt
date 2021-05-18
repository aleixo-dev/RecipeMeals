package com.nicolas.recipemeals.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.data.repository.databaserepository.RecipeRepositoryDaoImpl
import kotlinx.coroutines.launch

class FavoriteViewModel(private val recipeRepositoryDaoImpl: RecipeRepositoryDaoImpl) :
    ViewModel() {

    /** @Alterar: Isto, MutableLiveData<List<Meal>>() */
    private val _myRecipeList = MutableLiveData<List<Meal>>()
    val myRecipeList: LiveData<List<Meal>>
        get() = _myRecipeList

    init {
        getAllRecipeList()
    }

    private fun getAllRecipeList() {
        viewModelScope.launch {
            _myRecipeList.value = recipeRepositoryDaoImpl.getAllRecipe()
        }
    }

}