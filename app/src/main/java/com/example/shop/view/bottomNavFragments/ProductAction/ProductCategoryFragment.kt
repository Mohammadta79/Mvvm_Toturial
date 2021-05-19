package com.example.shop.view.bottomNavFragments.ProductAction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.adapter.ProductsCategoryAdapter
import com.example.shop.databinding.FragmentCategoryProductsBinding
import com.example.shop.model.ProductModel
import com.example.shop.viewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductCategoryFragment : Fragment(), onProductListItemClickListener {
    private lateinit var binding: FragmentCategoryProductsBinding
    private val viewModel by viewModels<ProductViewModel>()
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString("category", null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    private fun initViews(){
        binding.txtCategoryName.text = category

        viewModel.getProductsCategory(category).observe(viewLifecycleOwner){
            binding.categoryRV.apply {
                adapter = ProductsCategoryAdapter(requireContext(),it,this@ProductCategoryFragment)
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
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
        findNavController().navigate(R.id.action_productCategoryFragment_to_detailsProductFragment, bundle)
    }


}