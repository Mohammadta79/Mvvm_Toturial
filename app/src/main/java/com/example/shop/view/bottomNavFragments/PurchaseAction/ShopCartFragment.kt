package com.example.shop.view.bottomNavFragments.PurchaseAction

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.InterFaces.onShopCartItemCLickListener
import com.example.shop.R
import com.example.shop.adapter.ShopCartProductsAdapter
import com.example.shop.databinding.FragmentShopCartBinding
import com.example.shop.model.ManageCartResponseModel
import com.example.shop.model.ShopCartModel
import com.example.shop.viewModel.ShopCartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopCartFragment : Fragment(), onShopCartItemCLickListener, View.OnClickListener {
    private lateinit var binding: FragmentShopCartBinding
    private val shopCartViewModel by viewModels<ShopCartViewModel>()
    private lateinit var adapter: ShopCartProductsAdapter
    private var user_id: String? = null
    private lateinit var map: HashMap<String, String>
    var sharedPref: SharedPreferences? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShopCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        selectedViews()
    }

    fun initViews() {
        sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        sharedPref!!.getString("id", null).let {
            user_id = it
        }
        map = HashMap()

        shopCartViewModel.getShopCartLiveData(user_id)
            .observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    binding.btnGoNext.visibility = View.VISIBLE
                    binding.shopCartRV.apply {
                        adapter = ShopCartProductsAdapter(
                            requireContext(),
                            it,
                            this@ShopCartFragment
                        )
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )

                    }
                } else {
                    binding.txtEmptyCart.visibility = View.VISIBLE
                }


            }

        shopCartViewModel.getCartPrice(user_id.toString())
            .observe(viewLifecycleOwner) {
                if (it.price != null) {
                    binding.txtPrice.text = it.price + " تومان "
                }

            }


    }

    override fun onClick(shopCartModel: ShopCartModel) {
        var bundle = Bundle()
        bundle.putString("name", shopCartModel.name)
        bundle.putString("id", shopCartModel.idproduct)
        bundle.putString("category", shopCartModel.category)
        bundle.putString("price", shopCartModel.price)
        bundle.putString("desc", shopCartModel.describtion)
        bundle.putString("weight", shopCartModel.weight)
        bundle.putString("image", shopCartModel.image)
        bundle.putInt("favorite", shopCartModel.favorite)
        bundle.putInt("reminder", shopCartModel.reminder)
        findNavController().navigate(R.id.action_shopCartFragment_to_detailsProductFragment, bundle)
    }

    override fun onChangeCount(order: String, product_id: String): HashMap<String, String> {


        shopCartViewModel.manageShopCart(user_id.toString(), product_id, order)
            .observe(viewLifecycleOwner) {
                if (it.status == "delete") {
                    adapter.deleteItem(product_id.toInt())
                } else {
                    if (it.status == "ok") {
                        binding.txtPrice.text = it.cart_price
                        map["count"] = it.count
                        map["price"] = it.price
                    } else {
                        Toast.makeText(requireContext(), "خطا", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        return map
    }


    override fun onClick(v: View?) {
        if (v!!.id == binding.btnGoNext.id) {
            findNavController().navigate(R.id.action_shopCartFragment_to_completePurchaseFragment)
        }
    }

    private fun selectedViews() {
        binding.btnGoNext.setOnClickListener(this)
    }

}