package com.example.shop.view.bottomNavFragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.InterFaces.onHomeListClickListener
import com.example.shop.InterFaces.onHomeListItemClickListener
import com.example.shop.adapter.HomeProductsAdapter
import com.example.shop.adapter.HomeProductsListItemAdapter
import com.example.shop.data.LocalData
import com.example.shop.databinding.FragmentHomeBinding
import com.example.shop.model.CategoryModel
import com.example.shop.model.ProductModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(),onHomeListItemClickListener,onHomeListClickListener {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var bannerAdapter: BaseBannerAdapter
    lateinit var productsListItemAdapter: HomeProductsListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        //init top Banner
        binding.Banner.setAdapter(bannerAdapter)

        //init Adapters
        productsListItemAdapter = HomeProductsListItemAdapter(requireContext(),LocalData.productsItems(),this)
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter= HomeProductsAdapter(requireContext(),LocalData.categoryItems(),productsListItemAdapter,this@HomeFragment)
        }


    }

    override fun onHomeListItemClick(productModel: ProductModel) {
      //TODO : go to deatils fragments
    }

    override fun onHomeListClick(categoryModel: CategoryModel) {
        //TODO : Show all list of this category
    }

}