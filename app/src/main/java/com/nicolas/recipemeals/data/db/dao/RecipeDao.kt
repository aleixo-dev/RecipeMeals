package com.nicolas.recipemeals.data.db.dao

import androidx.room.*
import com.nicolas.recipemeals.data.db.entity.MealEntity

/**
 * @Alteracao:
 * -> TODO: alterar SELECT * FROM recipes para meals
 * -> TODO: tudo relacionado a RecipeEntity deve mudar para MealEntity.
 * -> TODO: Retornar uma lista de MealEntity e não RecipeEntity.
 * -> TODO: Insert e Delete deve receber como parametro um mealEntity.
 */

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mealEntity: MealEntity)

    @Delete
    suspend fun delete(mealEntity: MealEntity)

    @Query("SELECT * FROM meals")
    suspend fun getAll(): List<MealEntity>

}