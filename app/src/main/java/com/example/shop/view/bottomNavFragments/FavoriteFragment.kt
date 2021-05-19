package com.example.shop.view.bottomNavFragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.adapter.FavoriteProductsAdapter
import com.example.shop.databinding.FragmentFavoriteBinding

import com.example.shop.model.ProductModel
import com.example.shop.viewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(), onProductListItemClickListener {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    var sharedPref: SharedPreferences? = null
    private lateinit var user_id: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        user_id = sharedPref!!.getString("id", null).toString()

        user_id.let {
            favoriteViewModel.getFavoriteLiveData(it)
                .observe(requireActivity()) {
                    binding.favoriteRecyclerview.apply {
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                        adapter = FavoriteProductsAdapter(
                            requireContext(),
                            it,
                            this@FavoriteFragment
                        )
                    }
                }
        }


    }

    override fun onProductListItemClick(productModel: ProductModel) {
        var bundle = Bundle()
        bundle.putString("name", productModel.name)
        bundle.putString("id", productModel.id)
        bundle.putString("category", productModel.category)
        bundle.putString("price", productModel.price)
        bundle.putString("desc", productModel.describtion)
        bundle.putString("weight", productModel.weight)
        bundle.putString("image", productModel.image)
        bundle.putInt("reminder", productModel.reminder)
        findNavController().navigate(R.id.action_favoriteFragment_to_detailsProductFragment, bundle)
    }


}