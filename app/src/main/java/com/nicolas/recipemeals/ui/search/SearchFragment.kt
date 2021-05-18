package com.nicolas.recipemeals.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicolas.recipemeals.R
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.databinding.SearchFragmentBinding
import com.nicolas.recipemeals.ui.search.adapter.SearchViewAdapter
import com.nicolas.recipemeals.utils.Status
import com.nicolas.recipemeals.utils.toLoweCase
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.search_fragment) {

    private val viewModel: SearchViewModel by viewModel()
    private var bindingSearch: SearchFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingSearch = SearchFragmentBinding.bind(view)

        bindingSearch!!.tvSearchMeal.addTextChangedListener { searchMeal ->
            if (searchMeal.isNullOrEmpty()) {
                bindingSearch!!.recyclerSearchMeal.visibility = View.GONE
            } else {
                viewModel.searchMeal(toLoweCase(searchMeal.toString()))
            }
        }

        viewModel.searchMeal.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    bindingSearch!!.progressBarSearch.visibility = View.VISIBLE
                    bindingSearch!!.recyclerSearchMeal.visibility = View.GONE
                    bindingSearch!!.tvNotFound.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    if (it.data == null) {
                        bindingSearch!!.recyclerSearchMeal.visibility = View.GONE
                        bindingSearch!!.progressBarSearch.visibility = View.GONE
                        bindingSearch!!.tvNotFound.visibility = View.VISIBLE
                    } else {
                        bindingSearch!!.progressBarSearch.visibility = View.GONE
                        it.data?.let { listMeal -> showRecyclerView(listMeal) }
                        bindingSearch!!.recyclerSearchMeal.visibility = View.VISIBLE
                    }
                }
                Status.ERROR -> {
                    bindingSearch!!.tvNotFound.visibility = View.GONE
                    bindingSearch!!.recyclerSearchMeal.visibility = View.GONE
                    bindingSearch!!.progressBarSearch.visibility = View.GONE
                    bindingSearch!!.imgNoConnectionInternet.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showRecyclerView(listMeal: List<Meal>) {
        with(bindingSearch!!.recyclerSearchMeal) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = SearchViewAdapter(listMeal) {
                val directions = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}