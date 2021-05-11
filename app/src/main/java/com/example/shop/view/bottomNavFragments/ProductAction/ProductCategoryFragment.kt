package com.example.shop.view.bottomNavFragments.ProductAction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shop.R
import com.example.shop.databinding.FragmentCategoryProductsBinding


class ProductCategoryFragment : Fragment() {
private lateinit var binding:FragmentCategoryProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryProductsBinding.inflate(inflater,container,false)
        return binding.root

    }

}