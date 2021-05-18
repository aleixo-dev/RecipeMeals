package com.nicolas.recipemeals.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nicolas.recipemeals.data.model.Recipe
import com.nicolas.recipemeals.data.model.filter.Meal

/**
 * @Alteracao:
 * -> TODO alterar o nome da classe de RecipeEntity para MealEntity.
 * -> TODO mudar toRecipe para toMeal.
 * -> TODO retornar uma lista de Meal e não de Recipe.
 * -> TODO mudar tableName para 'meals'
 */

@Entity(tableName = "meals")
data class MealEntity(
    @field:PrimaryKey(autoGenerate = true) val idMeal: Int?,
    @field:ColumnInfo(name = "strMeal") val strMeal: String?,
    @field:ColumnInfo(name = "strMealThumb") val strMealThumb: String?,
    @field:ColumnInfo(name = "strInstructions") val strInstructions: String?,
    @field:ColumnInfo(name = "strArea") val strArea: String?,
    @field:ColumnInfo(name = "strCategory") val strCategory: String?,
)

fun toMeal(listRecipeMeal: List<MealEntity>): ArrayList<Meal> {
    val myListFavorite = ArrayList<Meal>()

    for (i in listRecipeMeal) {
        val favorite =
            Meal(
                i.idMeal.toString(),
                i.strMeal,
                i.strMealThumb,
                i.strInstructions,
                i.strArea,
                i.strCategory
            )
        myListFavorite.add(favorite)
    }
    return myListFavorite
}
