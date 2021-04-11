package com.example.shop.view.bottomNavFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.adapter.FavoriteProductsAdapter
import com.example.shop.data.LocalData
import com.example.shop.databinding.FragmentFavoriteBinding
import com.example.shop.model.ProductModel


class FavoriteFragment : Fragment(),onProductListItemClickListener {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    private fun initViews(){
        binding.favoriteRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL,false)
            adapter = FavoriteProductsAdapter(requireContext() ,LocalData.productsItems() ,this@FavoriteFragment)
        }
    }

    override fun onHomeListItemClick(productModel: ProductModel) {
        TODO("Not yet implemented")
    }
}