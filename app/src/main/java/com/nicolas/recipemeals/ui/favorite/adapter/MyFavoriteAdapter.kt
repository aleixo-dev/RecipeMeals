package com.nicolas.recipemeals.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.databinding.ItemMyListBinding

class MyFavoriteAdapter(
    private val listRecipe: List<Meal>,
    private val clickRecipeList: ((recipe: Meal) -> Unit)
) :
    RecyclerView.Adapter<MyFavoriteAdapter.ViewHolder>() {

    class ViewHolder(
        binding: ItemMyListBinding,
        private val clickRecipeList: ((recipe: Meal) -> Unit)
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private val imgDetailsList: ImageView = binding.imageDetailsList
        private val tvNameFavoriteMeal: TextView = binding.tvNameFavoriteMeal

        fun bind(meal: Meal) {
            setImageUrl(imgDetailsList, meal.strMealThumb)
            tvNameFavoriteMeal.text = meal.strMeal
            itemView.setOnClickListener {
                clickRecipeList.invoke(meal)
            }
        }

        private fun setImageUrl(imageView: ImageView, imgUrl: String?) {
            imgUrl?.let {
                val imgUri = it.toUri().buildUpon().scheme("https").build()
                Glide.with(imageView.context)
                    .load(imgUri)
                    .centerCrop()
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view, clickRecipeList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listRecipe[position])
    }

    override fun getItemCount() = listRecipe.size
}