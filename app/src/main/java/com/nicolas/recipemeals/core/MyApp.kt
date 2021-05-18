package com.nicolas.recipemeals.core

import android.app.Application
import com.nicolas.recipemeals.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                initApiModule,
                categoriesModule,
                filterMealsModule,
                detailsMealsModule,
                databaseModule,
                myFavoriteModule,
                searchModule
            )
        }
    }
}