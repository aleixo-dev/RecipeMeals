package com.nicolas.recipemeals.ui.listmeals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.nicolas.recipemeals.R
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.databinding.MealFragmentBinding
import com.nicolas.recipemeals.ui.listmeals.adapter.MealViewAdapter
import com.nicolas.recipemeals.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MealFragment : Fragment(R.layout.meal_fragment) {

    private val args: MealFragmentArgs by navArgs()
    private val viewModel: MealViewModel by viewModel()
    private var _binding: MealFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MealFragmentBinding.bind(view)

        /*Recebe o objeto category por meio de safeargs.*/
        val listFilterMeals = args.category

        /*Faz chamda na api com id do objeto recebido.*/
        listFilterMeals.strCategory.let {
            viewModel.getFilterMeals(it)
        }

        _binding!!.imgArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.filterMeals.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    _binding!!.progressBarMeal.visibility = View.VISIBLE
                    _binding!!.myRecyclerMealList.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    it.data?.let { it1 -> showMealList(it1) }
                    _binding!!.progressBarMeal.visibility = View.GONE
                    _binding!!.myRecyclerMealList.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    _binding!!.progressBarMeal.visibility = View.GONE
                    _binding!!.imgViewNoConnectionList.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showMealList(listMeal: List<Meal>) {
        with(_binding!!.myRecyclerMealList) {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = MealViewAdapter(listMeal) {
                val directions = MealFragmentDirections.actionMealFragmentToDetailsFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}