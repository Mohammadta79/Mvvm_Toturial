package com.example.shop.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shop.R
import com.example.shop.databinding.FragmentDetailsProductBinding
import com.example.shop.viewModel.ProductViewModel
import com.example.shop.viewModel.ShopCartViewModel
import com.squareup.picasso.Picasso


class ProductDetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentDetailsProductBinding
    lateinit var name: String
    lateinit var price: String
    lateinit var desc: String
    lateinit var category: String
    lateinit var weight: String
    lateinit var startPoint: String
    lateinit var image: String
    var favorite: Int = 0
    var reminder: Int = 0
    lateinit var id: String
    lateinit var productViewModel: ProductViewModel
    lateinit var shopCartViewModel: ShopCartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name", null)
            price = it.getString("price", null)
            desc = it.getString("desc", null)
            category = it.getString("category", null)
            startPoint = it.getString("startPoint", null)
            weight = it.getString("weight", null)
            image = it.getString("image", null)
            reminder = it.getInt("reminder", 0)
            favorite = it.getInt("favorite", 0)
            id = it.getString("id", null)

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
        val sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        when (v!!.id) {
            binding.btnAddToCart.id -> {
                if (startPoint.equals("shopCart")) {
                    Toast.makeText(
                        requireContext(),
                        "این محصول در سبد شما موجود است",
                        Toast.LENGTH_LONG
                    ).show()

                } else {

                    if (sharedPref!!.getString("id", null) == null) {
                        Toast.makeText(
                            requireContext(),
                            "لطفا ابتدا ثبت نام کنید(در صفحه فروشگاه من عبارت ثبت نام وجود دارد)",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        sharedPref!!.getString("id", null)
                            ?.let {
                                val res = shopCartViewModel.addTocart(it, id, "add")
                                res.observe(requireActivity(), {
                                    reminder = it.reminder
                                    if (reminder == 0) {
                                        binding.imgAddCart.isEnabled = false
                                    }
                                    binding.btnAddToCart.visibility = View.INVISIBLE
                                    binding.cardHandelCart.visibility = View.VISIBLE
                                })
                            }

                    }
                }


            }
            binding.imgFav.id -> {
                val res = productViewModel.setFav(id, favorite)
                res.observe(requireActivity(), {
                    if (it == "like") {
                        binding.imgFav.setImageResource(R.drawable.ic_like)
                    } else {
                        binding.imgFav.setImageResource(R.drawable.ic_disslike)
                    }
                })
            }
            binding.imgAddCart.id -> {

                sharedPref!!.getString("id", null)
                    ?.let {
                        val res = shopCartViewModel.addTocart(it, id, "add")
                        res.observe(requireActivity(), {
                            reminder = it.reminder
                            if (reminder == 0) {
                                binding.imgAddCart.isEnabled = false
                            }
                        })
                    }
            }
            binding.imgMinesCart.id -> {

                sharedPref!!.getString("id", null)
                    ?.let {
                        if (binding.imgMinesCart.drawable == getDrawable(
                                requireContext(),
                                R.drawable.ic_delete
                            )
                        ) {
                            val res = shopCartViewModel.addTocart(it, id, "delete")
                            res.observe(requireActivity(), {
                                if (it.status == "ok") {
                                    binding.btnAddToCart.visibility = View.VISIBLE
                                    binding.btnAddToCart.isEnabled = true
                                    binding.cardHandelCart.visibility = View.INVISIBLE
                                }
                            })

                        } else {
                            val res = shopCartViewModel.addTocart(it, id, "mines")
                            res.observe(requireActivity(), {
                                reminder = it.reminder
                                if (it.count.toInt() == 0) {
                                    binding.imgMinesCart.setImageResource(R.drawable.ic_delete)
                                }
                            })
                        }

                    }
            }
        }
    }

    fun selectedViews() {
        binding.btnAddToCart.setOnClickListener(this)
        binding.imgFav.setOnClickListener(this)
        binding.imgMinesCart.setOnClickListener(this)
        binding.imgAddCart.setOnClickListener(this)
    }

    private fun initViews() {
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        shopCartViewModel = ViewModelProvider(requireActivity()).get(shopCartViewModel::class.java)

        when (favorite) {
            0 -> {
                binding.imgFav.setImageResource(R.drawable.ic_disslike)
            }
            1 -> {
                binding.imgFav.setImageResource(R.drawable.ic_like)
            }
        }
        if (reminder == 0) {
            binding.btnAddToCart.text = "اتمام موجودی!"
            binding.btnAddToCart.isEnabled = false
        }

    }

}