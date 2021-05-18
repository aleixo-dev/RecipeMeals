package com.nicolas.recipemeals.di

import androidx.room.Room
import com.nicolas.recipemeals.data.api.CategoriesApiService
import com.nicolas.recipemeals.data.db.RecipeDatabase
import com.nicolas.recipemeals.data.repository.ApiRepositoryImpl
import com.nicolas.recipemeals.data.repository.databaserepository.RecipeRepositoryDaoImpl
import com.nicolas.recipemeals.ui.categories.CategoriesViewModel
import com.nicolas.recipemeals.ui.details.DetailsViewModel
import com.nicolas.recipemeals.ui.favorite.FavoriteViewModel
import com.nicolas.recipemeals.ui.listmeals.MealViewModel
import com.nicolas.recipemeals.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object KoinModules {
    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
}

val initApiModule = module {
    single { provideRetrofit() }
    single { provideCategoriesApiService(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(), RecipeDatabase::class.java,
            "recipes-meals"
        ).build()
    }
    single { get<RecipeDatabase>().recipeDao }
}

val categoriesModule = module {

    viewModel {
        CategoriesViewModel(
            ApiRepositoryImpl(
                categoriesApiService = get()
            )
        )
    }
}

val filterMealsModule = module {

    viewModel {
        MealViewModel(
            ApiRepositoryImpl(
                categoriesApiService = get()
            )
        )
    }
}

val detailsMealsModule = module {
    viewModel {
        DetailsViewModel(
            ApiRepositoryImpl(
                categoriesApiService = get()
            ), RecipeRepositoryDaoImpl(
                recipeDao = get()
            )
        )
    }
}

val myFavoriteModule = module {
    viewModel {
        FavoriteViewModel(
            RecipeRepositoryDaoImpl(
                recipeDao = get()
            )
        )
    }
}

val searchModule = module {
    viewModel {
        SearchViewModel(
            ApiRepositoryImpl(
                categoriesApiService = get()
            )
        )
    }
}

private fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(KoinModules.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

private fun provideCategoriesApiService(retrofit: Retrofit): CategoriesApiService =
    retrofit.create(CategoriesApiService::class.java)
