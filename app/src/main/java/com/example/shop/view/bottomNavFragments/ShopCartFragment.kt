package com.example.shop.view.bottomNavFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.R
import com.example.shop.adapter.ShopCartProductsAdapter
import com.example.shop.data.LocalData
import com.example.shop.databinding.FragmentShopCartBinding

class ShopCartFragment : Fragment() {
    private lateinit var binding: FragmentShopCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShopCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    fun initViews(){
        binding.shopCartRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = ShopCartProductsAdapter(requireContext(),LocalData.shopcartProduct())

        }
    }

}