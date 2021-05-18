package com.nicolas.recipemeals.ui.listmeals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.databinding.ItemsMealsRecyclerViewBinding

class MealViewAdapter(
    private val listMeal: List<Meal>,
    private val clickMeal: ((meal: Meal) -> Unit)
) :
    RecyclerView.Adapter<MealViewAdapter.ViewHolder>() {
    class ViewHolder(
        binding: ItemsMealsRecyclerViewBinding,
        private val clickMeal: ((meal: Meal) -> Unit)
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private val nameMeal: TextView = binding.tvNameMael
        private val imgMeal: ImageView = binding.meal

        fun bind(meal: Meal) {
            nameMeal.text = meal.strMeal
            setImageUrl(imgMeal, meal.strMealThumb)

            itemView.setOnClickListener {
                clickMeal.invoke(meal)
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
        val layout = ItemsMealsRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(layout, clickMeal)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMeal[position])
    }

    override fun getItemCount() = listMeal.size
}