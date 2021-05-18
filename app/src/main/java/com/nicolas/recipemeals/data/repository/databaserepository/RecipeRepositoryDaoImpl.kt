package com.nicolas.recipemeals.data.repository.databaserepository

import com.nicolas.recipemeals.data.db.dao.RecipeDao
import com.nicolas.recipemeals.data.db.entity.toMeal
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.data.model.filter.toEntity

/** Alterar: Isto! */

class RecipeRepositoryDaoImpl(private val recipeDao: RecipeDao) : RecipeRepository {

    /** @Alterar: Isto, parâmetro de recipe para meal */
    override suspend fun insertRecipe(meal: Meal) {
        recipeDao.insert(toEntity(meal))
    }

    /** @Alterar: Isto, parâmetro de recipe para meal */
    override suspend fun deleteRecipe(meal: Meal) {
        recipeDao.delete(toEntity(meal))
    }

    /** @Alterar: Isto, retornar uma lista de Meal! */
    override suspend fun getAllRecipe(): List<Meal> {
        return toMeal(recipeDao.getAll())
    }
}