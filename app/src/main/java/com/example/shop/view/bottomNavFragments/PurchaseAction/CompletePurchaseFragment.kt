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
import com.example.shop.adapter.CompletePurchaseProductsAdapter
import com.example.shop.databinding.FragmentCompletePurchaseBinding
import com.example.shop.model.ShopCartModel
import com.example.shop.viewModel.AddressViewModel
import com.example.shop.viewModel.ShopCartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompletePurchaseFragment : Fragment(), onShopCartItemCLickListener, View.OnClickListener {

    private lateinit var binding: FragmentCompletePurchaseBinding
    private  val shopCartViewModel by viewModels<ShopCartViewModel>()
    private  val addressViewModel by viewModels<AddressViewModel>()
    var total_price = 0
    var sharedPref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCompletePurchaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        selectdViews()

    }

    fun initViews() {
        sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        sharedPref!!.getString("id", null)?.let { it ->
            shopCartViewModel.getShopCartLiveData(it).observe(requireActivity()) {
                binding.completePurchaseRecycler.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
                    adapter = CompletePurchaseProductsAdapter(
                        requireContext(),
                        it,
                        this@CompletePurchaseFragment
                    )
                }
                it.forEachIndexed { index, _ ->
                    total_price += it[index].price.toInt()
                }
                binding.txtFinalPrice.text = total_price.toString()
            }
            addressViewModel.getCurrentAddress(it).observe(requireActivity()) {
                binding.txtAddress.text = it.address
                binding.txtCity.text = it.city
                binding.txtPostalCade.text = it.postalCode
                binding.txtReciver.text = it.reciver
                binding.txtPhoneNumber.text = it.mobile

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
        bundle.putString("startPoint", "shopCart")
        findNavController().navigate(
            R.id.action_completePurchaseFragment_to_detailsProductFragment,
            bundle
        )
    }

    private fun selectdViews() {
        binding.btnPay.setOnClickListener(this)
    }

    override fun onChangeCount(order: String, id: String) {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            binding.btnPay.id -> {
                sharedPref!!.getString("id", null)?.let {
                    shopCartViewModel.pay(it).observe(requireActivity()) {
                        if (it == "ok") {
                            Toast.makeText(
                                requireContext(),
                                "خرید شما با موفقیت انجام شد",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(requireContext(), "خطایی رخ داده است", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }


}