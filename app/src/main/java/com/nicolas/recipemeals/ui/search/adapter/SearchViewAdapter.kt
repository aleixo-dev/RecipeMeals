package com.nicolas.recipemeals.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.databinding.ItemsSearchViewBinding

class SearchViewAdapter(
    private val listSearchMeal: List<Meal>,
    private val clickRecipeList: ((recipe: Meal) -> Unit)
) : RecyclerView.Adapter<SearchViewAdapter.MyViewHolder>() {

    class MyViewHolder(
        binding: ItemsSearchViewBinding,
        private val clickRecipeList: ((recipe: Meal) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        private val imgViewSearch: ImageView = binding.imgViewSearch
        private val tvSearchName: TextView = binding.tvSearchName
        private val tvSearchCategory: TextView = binding.tvSearchCategory

        fun bind(meal: Meal) {
            setImageUrl(imgViewSearch, meal.strMealThumb)
            tvSearchName.text = meal.strMeal
            tvSearchCategory.text = meal.strCategory
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            ItemsSearchViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view, clickRecipeList)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listSearchMeal[position])
    }

    override fun getItemCount() = listSearchMeal.size
}