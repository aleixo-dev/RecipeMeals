package com.nicolas.recipemeals.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicolas.recipemeals.R
import com.nicolas.recipemeals.data.model.categories.Category
import com.nicolas.recipemeals.databinding.CategoriesFragmentBinding
import com.nicolas.recipemeals.ui.categories.adapter.CategoriesViewAdapter
import com.nicolas.recipemeals.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesFragment : Fragment(R.layout.categories_fragment) {

    private val viewModel: CategoriesViewModel by viewModel()
    private var _binding: CategoriesFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CategoriesFragmentBinding.bind(view)
        initCallCategories()
    }

    private fun initCallCategories() {
        viewModel.categoriesData.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    _binding!!.myProgressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    _binding!!.myProgressBar.visibility = View.GONE
                    it.data?.let { listCategoryRecycler ->
                        showRecyclerView(listCategoryRecycler)
                    }
                    _binding!!.myRecyclerViewRecipe.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    _binding!!.myRecyclerViewRecipe.visibility = View.GONE
                    _binding!!.myProgressBar.visibility = View.GONE
                    _binding!!.imgViewNoConnection.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showRecyclerView(categoryList: List<Category>) {
        with(_binding!!.myRecyclerViewRecipe) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = CategoriesViewAdapter(categoryList) {
                val directions =
                    CategoriesFragmentDirections.actionCategoriesFragmentToMealFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}