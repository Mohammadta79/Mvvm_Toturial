package com.example.shop.view.bottomNavFragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.InterFaces.onHomeListClickListener
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.adapter.HomeProductsAdapter
import com.example.shop.adapter.ProductsListItemAdapter
import com.example.shop.data.LocalData
import com.example.shop.databinding.FragmentHomeBinding
import com.example.shop.model.CategoryModel
import com.example.shop.model.ProductModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment : Fragment(),onProductListItemClickListener,onHomeListClickListener {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var bannerAdapter: BaseBannerAdapter
    lateinit var productsListItemAdapter: ProductsListItemAdapter

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
        productsListItemAdapter = ProductsListItemAdapter(requireContext(),LocalData.productsItems(),this)
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter= HomeProductsAdapter(requireContext(),LocalData.categoryItems(),productsListItemAdapter,this@ProductFragment)
        }


    }

    override fun onHomeListItemClick(productModel: ProductModel) {
        var  bundle = Bundle()
        bundle.putString("name",productModel.name)
        bundle.putString("category",productModel.category)
        bundle.putString("price",productModel.price)
        bundle.putString("desc",productModel.desc)
      findNavController().navigate(R.id.action_homeFragment_to_detailsProductFragment,bundle)
    }


    override fun onHomeListClick(categoryModel: CategoryModel) {
        //TODO : Show all list of this category
    }

}