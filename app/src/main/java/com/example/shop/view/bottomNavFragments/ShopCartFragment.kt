package com.example.shop.view.bottomNavFragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.InterFaces.onShopCartItemCLickListener
import com.example.shop.R
import com.example.shop.adapter.ShopCartProductsAdapter
import com.example.shop.data.LocalData
import com.example.shop.databinding.FragmentShopCartBinding
import com.example.shop.model.ShopCartModel
import com.example.shop.viewModel.ShopCartViewModel

class ShopCartFragment : Fragment(), onShopCartItemCLickListener {
    private lateinit var binding: FragmentShopCartBinding
    private lateinit var shopCartViewModel: ShopCartViewModel
    private lateinit var liveData: MutableLiveData<ArrayList<ShopCartModel>>

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

    fun initViews() {
        shopCartViewModel = ViewModelProvider(requireActivity()).get(ShopCartViewModel::class.java)
        val sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        sharedPref!!.getString("id", null)?.let {
            liveData = shopCartViewModel.getShopCartLiveData(it)
        }
        liveData.observe(requireActivity(), {
            binding.shopCartRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = ShopCartProductsAdapter(
                    requireContext(),
                    it,
                    this@ShopCartFragment
                )
            }
        })


    }

    override fun onClick(shopCartModel: ShopCartModel) {
        var bundle = Bundle()
        bundle.putString("name", shopCartModel.name)
        bundle.putString("id", shopCartModel.id)
        bundle.putString("category", shopCartModel.category)
        bundle.putString("price", shopCartModel.price)
        bundle.putString("desc", shopCartModel.desc)
        bundle.putString("weight", shopCartModel.weight)
        bundle.putString("image", shopCartModel.image)
        bundle.putInt("favorite", shopCartModel.favorite)
        bundle.putInt("reminder", shopCartModel.reminder)
        bundle.putString("startPoint", "shopCart")
        findNavController().navigate(R.id.action_shopCartFragment_to_detailsProductFragment, bundle)
    }

}