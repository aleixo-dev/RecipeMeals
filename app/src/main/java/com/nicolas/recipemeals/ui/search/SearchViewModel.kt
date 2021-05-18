package com.nicolas.recipemeals.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.data.repository.ApiRepositoryImpl
import com.nicolas.recipemeals.utils.Resource
import kotlinx.coroutines.launch

class SearchViewModel(private val apiRepositoryImpl: ApiRepositoryImpl) : ViewModel() {

    private val _searchMeal = MutableLiveData<Resource<List<Meal>>>()
    val searchMeal: LiveData<Resource<List<Meal>>>
        get() = _searchMeal


    fun searchMeal(searchMealName: String) {
        viewModelScope.launch {
            _searchMeal.postValue(Resource.loading(null))
            try {
                _searchMeal.postValue(
                    Resource.success(
                        apiRepositoryImpl.getSearchMeal(
                            searchMealName
                        )
                    )
                )
            } catch (e: Exception) {
                _searchMeal.postValue(
                    Resource.error(
                        "Error,please check you connection internet.",
                        null
                    )
                )
            }
        }
    }
}