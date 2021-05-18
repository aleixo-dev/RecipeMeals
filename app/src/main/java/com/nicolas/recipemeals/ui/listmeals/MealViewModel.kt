package com.nicolas.recipemeals.ui.listmeals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.data.repository.ApiRepositoryImpl
import com.nicolas.recipemeals.utils.Resource
import kotlinx.coroutines.launch

class MealViewModel(private val apiRepositoryImpl: ApiRepositoryImpl) : ViewModel() {

    private val _filterMeals = MutableLiveData<Resource<List<Meal>>>()
    val filterMeals: LiveData<Resource<List<Meal>>>
        get() = _filterMeals

    fun getFilterMeals(filterMeals: String) {
        viewModelScope.launch {
            _filterMeals.postValue(Resource.loading(null))
            try {
                _filterMeals.postValue(Resource.success(apiRepositoryImpl.getFilterMeals(filterMeals)))
            } catch (e: Exception) {
                _filterMeals.postValue(Resource.error("Error", null))
            }
        }
    }
}