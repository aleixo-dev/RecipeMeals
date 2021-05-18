package com.nicolas.recipemeals.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nicolas.recipemeals.R
import com.nicolas.recipemeals.data.model.Recipe
import com.nicolas.recipemeals.data.model.filter.Meal
import com.nicolas.recipemeals.databinding.FavoriteFragmentBinding
import com.nicolas.recipemeals.ui.favorite.adapter.MyFavoriteAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(R.layout.favorite_fragment) {

    private val viewModel: FavoriteViewModel by viewModel()
    private var _binding: FavoriteFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FavoriteFragmentBinding.bind(view)

        viewModel.myRecipeList.observe(viewLifecycleOwner, {
            with(_binding!!.myRecyclerList) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)
                adapter = MyFavoriteAdapter(it) { meal ->
                    val directions =
                        FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(meal)
                    findNavController().navigate(directions)
                }
            }
        })
    }
}