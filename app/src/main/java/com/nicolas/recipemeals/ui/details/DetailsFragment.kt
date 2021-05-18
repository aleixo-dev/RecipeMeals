package com.nicolas.recipemeals.ui.details

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nicolas.recipemeals.R
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.databinding.DetailsFragmentBinding
import com.nicolas.recipemeals.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val viewModel: DetailsViewModel by viewModel()
    private val argument: DetailsFragmentArgs by navArgs()
    private lateinit var binding: DetailsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailsFragmentBinding.bind(view)

        /*Recebe o objeto meal por meio de safeargs.*/
        val meal = argument.meal
        /*Faz um request na api, em 'lookup.php?={idMeal}' do objeto recebido.*/
        meal.idMeal.let {
            if (it != null) {
                viewModel.fetchDetailsMeals(it)
            }
        }

        binding.imageBackDetails.setOnClickListener {
            findNavController().popBackStack()
        }

        saveRecipeMyList(meal)

        viewModel.detailsMeals.observe(viewLifecycleOwner, { it ->
            when (it.status) {
                Status.LOADING -> {
                    binding.detailsProgressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.tvDetailsName.text = it.data?.get(0)?.strInstructions
                    binding.tvNameMealDetails.text = meal.strMeal
                    binding.tvStrArea.text = it.data?.get(0)?.strArea
                    binding.tvStrCategory.text = it.data?.get(0)?.strCategory
                    configureDetailsImage(binding.imageViewDetailsMeal, meal.strMealThumb!!)
                    binding.detailsProgressBar.visibility = View.GONE
                    binding.scrollDetails.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.detailsProgressBar.visibility = View.GONE
                    binding.scrollDetails.visibility = View.GONE
                    binding.imgViewNoConnectionDetails.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun configureDetailsImage(imageView: ImageView, imageUrl: String) {
        imageUrl.let {
            val imgUri = it.toUri().buildUpon().scheme("https").build()
            Glide.with(imageView.context)
                .load(imgUri)
                .centerCrop()
                .into(imageView)
        }
    }

    private fun saveRecipeMyList(meal: Meal) {
        viewModel.isRecipeMyList(meal)
        viewModel.isRecipeMyList.observe(viewLifecycleOwner, {
            if (it) {
                binding.imageSaveRecipeDetails.setOnClickListener(removeRecipe(meal))
                binding.imageSaveRecipeDetails.setImageResource(R.drawable.ic_round_turned_in_24)
            } else {
                binding.imageSaveRecipeDetails.setImageResource(R.drawable.ic_baseline_turned_in_not_24)
                binding.imageSaveRecipeDetails.setOnClickListener(addRecipe(meal))
            }
        })
    }

    private fun addRecipe(meal: Meal) = View.OnClickListener {
        viewModel.insertRecipeMyList(meal)
        binding.imageSaveRecipeDetails.setImageResource(R.drawable.ic_round_turned_in_24)
        Toast.makeText(requireContext(), "Added favorite list", Toast.LENGTH_SHORT).show()
    }

    private fun removeRecipe(meal: Meal) = View.OnClickListener {
        viewModel.removeRecipeMyList(meal)
        binding.imageSaveRecipeDetails.setImageResource(R.drawable.ic_baseline_turned_in_not_24)
        Toast.makeText(requireContext(), "Removed from favorite list.", Toast.LENGTH_SHORT).show()
    }
}