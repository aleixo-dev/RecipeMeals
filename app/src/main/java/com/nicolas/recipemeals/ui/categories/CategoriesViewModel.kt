package com.nicolas.recipemeals.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.recipemeals.data.model.categories.Category
import com.nicolas.recipemeals.data.repository.ApiRepositoryImpl
import com.nicolas.recipemeals.utils.Resource
import kotlinx.coroutines.launch

class CategoriesViewModel(private val apiServiceRepositoryImpl: ApiRepositoryImpl) :
    ViewModel() {

    private val _categoriesData = MutableLiveData<Resource<List<Category>>>()
    val categoriesData: LiveData<Resource<List<Category>>>
        get() = _categoriesData

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _categoriesData.postValue(Resource.loading(null))
            try {
                _categoriesData.postValue(Resource.success(apiServiceRepositoryImpl.getAllCategories()))
            } catch (e: Exception) {
                _categoriesData.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }
}