package com.example.shop.view.bottomNavFragments.ProductAction

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.shop.R
import com.example.shop.databinding.FragmentDetailsProductBinding
import com.example.shop.viewModel.ProductViewModel
import com.example.shop.viewModel.ShopCartViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(), View.OnClickListener {

    //TODO: get & set favorite value
    private lateinit var binding: FragmentDetailsProductBinding
    private lateinit var name: String
    private lateinit var price: String
    private lateinit var desc: String
    private lateinit var category: String
    private lateinit var weight: String
    private lateinit var image: String
    private var reminder: Int = 0
    private lateinit var product_id: String
    private lateinit var user_id: String
    private val productViewModel by viewModels<ProductViewModel>()
    private val shopCartViewModel by viewModels<ShopCartViewModel>()
    private var sharedPref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name", null)
            price = it.getString("price", null)
            desc = it.getString("desc", null)
            category = it.getString("category", null)
            weight = it.getString("weight", null)
            image = it.getString("image", null)
            reminder = it.getInt("reminder", 0)
            product_id = it.getString("id", null)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setData()
        selectedViews()

    }

    private fun setData() {
        binding.collapsingToolbar.title = name
        binding.txtPrice.text = price
        binding.txtProductWeight.text = weight
        binding.txtProductDescription.text = desc
        binding.txtCategory.text = category
        Picasso.get().load(image).into(binding.imgProduct)
    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            binding.btnAddToCart.id -> {
                shopCartViewModel.addToShopCart(user_id.toInt(), product_id.toInt())
                    .observe(viewLifecycleOwner) {

                            when (it.status) {
                                "ok" -> {
                                    binding.btnAddToCart.visibility = View.GONE
                                    binding.txtGoToShopCart.visibility = View.VISIBLE
                                }
                                else -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "خطایی رخ داده است",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    binding.btnAddToCart.visibility = View.VISIBLE
                                    binding.txtGoToShopCart.visibility = View.GONE
                                }
                            }

                    }


            }
//            binding.imgFav.id -> {
//                val res = productViewModel.setFav(id, favorite)
//                res.observe(requireActivity()) {
//                    if (it == "like") {
//                        binding.imgFav.setImageResource(R.drawable.ic_like)
//                    } else {
//                        binding.imgFav.setImageResource(R.drawable.ic_disslike)
//                    }
//                }
//            }

            binding.txtGoToShopCart.id -> {
                findNavController().navigate(R.id.action_detailsProductFragment_to_shopCartFragment)
            }

        }
    }

    fun selectedViews() {
        binding.btnAddToCart.setOnClickListener(this)
        binding.txtGoToShopCart.setOnClickListener(this)
        binding.imgFav.setOnClickListener(this)
    }

    private fun initViews() {
        sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        sharedPref!!.getString("id", null)
            ?.let {
                user_id = it
            }
        if (user_id == null) {
            binding.imgFav.visibility = View.GONE
        }

//        when (favorite) {
//            0 -> {
//                binding.imgFav.setImageResource(R.drawable.ic_disslike)
//            }
//            1 -> {
//                binding.imgFav.setImageResource(R.drawable.ic_like)
//            }
//        }
        if (reminder == 0) {
            binding.btnAddToCart.text = "اتمام موجودی!"
            binding.btnAddToCart.isEnabled = false
        }
        shopCartViewModel.checkShopCart(user_id.toInt(), product_id.toInt())
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    "exist" -> {
                        binding.btnAddToCart.visibility = View.GONE
                        binding.txtGoToShopCart.visibility = View.VISIBLE
                    }
                    "notExist" -> {
                        binding.btnAddToCart.visibility = View.VISIBLE
                        binding.txtGoToShopCart.visibility = View.GONE
                    }
                }

            }
    }

}