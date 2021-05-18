package com.nicolas.recipemeals.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicolas.recipemeals.data.db.dao.RecipeDao
import com.nicolas.recipemeals.data.db.entity.MealEntity

/**
 * @Alteracao:
 * -> TODO> Alterar o RecipeEntity para MealEntity.
 */
@Database(entities = [MealEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract val recipeDao: RecipeDao

    companion object {
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipes-meals"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}