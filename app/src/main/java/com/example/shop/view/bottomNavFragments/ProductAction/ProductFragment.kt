package com.example.shop.view.bottomNavFragments.ProductAction


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.adapter.CategoryListAdapter
import com.example.shop.adapter.BestSellerProductAdapter
import com.example.shop.adapter.OfferProductAdapter
import com.example.shop.databinding.FragmentProductBinding
import com.example.shop.model.ProductModel
import com.example.shop.viewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class ProductFragment : Fragment(), onProductListItemClickListener {

    private lateinit var binding: FragmentProductBinding

    @Inject
    @Named("ProductFragment")
    lateinit var bannerAdapter: BaseBannerAdapter

    private val productViewModel by viewModels<ProductViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        // productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        //init RV category
        productViewModel.getCategory().observe(viewLifecycleOwner) {
            binding.categoryRV.apply {
                layoutManager =
                    GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
                adapter = CategoryListAdapter(
                    requireContext(),
                    it
                )
            }
        }

        //init RV bestSellers
        productViewModel.bestSellers().observe(viewLifecycleOwner) {
            binding.bestSellersRV.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = BestSellerProductAdapter(
                    requireContext(),
                    it,
                    this@ProductFragment
                )
            }
        }


       //init RV Offers
        productViewModel.getOffers().observe(viewLifecycleOwner) {
            binding.offersRV.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = OfferProductAdapter(
                    requireContext(),
                    it,
                    this@ProductFragment
                )
            }
        }

        //init home banner
        binding.Banner.setAdapter(bannerAdapter)


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
        bundle.putString("startPoint", "home")
        findNavController().navigate(R.id.action_homeFragment_to_detailsProductFragment, bundle)
    }


}