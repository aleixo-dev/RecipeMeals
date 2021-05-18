package com.nicolas.recipemeals.ui.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicolas.recipemeals.R
import com.nicolas.recipemeals.data.model.categories.Category
import com.nicolas.recipemeals.databinding.ItemsRecyclerViewRecipeBinding

class CategoriesViewAdapter(
    private val listCategory: List<Category>,
    private val clickCategory: ((category: Category) -> Unit)
) : RecyclerView.Adapter<CategoriesViewAdapter.MyViewHolder>() {

    class MyViewHolder(
        private val _binding: ItemsRecyclerViewRecipeBinding,
        private val clickCategory: ((category: Category) -> Unit)
    ) : RecyclerView.ViewHolder(_binding.root) {

        private val tvCategory: TextView = _binding.tvNameCategory
        private val tvCategoryDescription: TextView = _binding.tvCategoryDescription
        private val imgViewCategory: ImageView = _binding.imgCategory

        fun bind(category: Category) {
            tvCategory.text = category.strCategory
            tvCategoryDescription.text = category.strCategoryDescription
            setImageUrl(imgViewCategory, category.strCategoryThumb)
            itemView.setOnClickListener {
                clickCategory.invoke(category)
            }
        }

        /*Config image in imageView*/
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

        val binding = ItemsRecyclerViewRecipeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(binding, clickCategory)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listCategory[position])
    }

    override fun getItemCount() = listCategory.size
}