package com.example.shop.view.bottomNavFragments.ProductAction


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.adapter.HomeProductsAdapter
import com.example.shop.adapter.ProductsListItemAdapter
import com.example.shop.databinding.FragmentHomeBinding
import com.example.shop.model.CategoryModel
import com.example.shop.model.ProductModel
import com.example.shop.viewModel.ProductViewModel

class ProductFragment : Fragment(), onProductListItemClickListener {

    private lateinit var binding: FragmentHomeBinding

    lateinit var productViewModel: ProductViewModel
    lateinit var mutableLiveData: MutableLiveData<ArrayList<CategoryModel>>


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
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        mutableLiveData = productViewModel.getProductLiveData()
        mutableLiveData.observe(requireActivity()) {

            it.forEachIndexed{
                    index, _ ->
                binding.homeRecyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = HomeProductsAdapter(
                        requireContext(),
                        it,
                        ProductsListItemAdapter(requireContext(), it[index].list, this@ProductFragment)
                    )
                }
            }

        }

        productViewModel.getBannerItem().observe(requireActivity()) {
            binding.Banner.setAdapter(BaseBannerAdapter(requireContext(), it))
        }


    }

    override fun onProductListItemClick(productModel: ProductModel) {
        var bundle = Bundle()
        bundle.putString("name", productModel.name)
        bundle.putString("id", productModel.id)
        bundle.putString("category", productModel.category)
        bundle.putString("price", productModel.price)
        bundle.putString("desc", productModel.desc)
        bundle.putString("weight", productModel.weight)
        bundle.putString("image", productModel.image)
        bundle.putInt("favorite", productModel.favorite)
        bundle.putInt("reminder", productModel.reminder)
        bundle.putString("startPoint", "home")
        findNavController().navigate(R.id.action_homeFragment_to_detailsProductFragment, bundle)
    }



}