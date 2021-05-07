package com.example.shop.view.bottomNavFragments.UserAction

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.adapter.BestSellerProductAdapter
import com.example.shop.databinding.FragmentUserBinding
import com.example.shop.model.ProductModel
import com.example.shop.view.Auth.AuthActivity
import com.example.shop.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class UserFragment : Fragment(), onProductListItemClickListener, View.OnClickListener {

    @Inject
    @Named("UserFragment")
    lateinit var bannerAdapter: BaseBannerAdapter
    private lateinit var binding: FragmentUserBinding
    private val userViewModel by viewModels<UserViewModel>()
    var sharedPref: SharedPreferences? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        selectedViews()

    }

    fun initView() {
        sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        when (sharedPref!!.getString("status", "logout")) {
            "login" -> {
                binding.orginalLayout.visibility = View.VISIBLE
                binding.registerLayout.visibility = View.GONE
            }
            "logout" -> {
                binding.orginalLayout.visibility = View.GONE
                binding.registerLayout.visibility = View.VISIBLE
            }
        }



        if (sharedPref!!.getString("id", null) != null) {
            userViewModel.getMyProductLiveData(sharedPref!!.getString("id", null))
                .observe(requireActivity()) {
                    binding.userRecyclerView.apply {
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                true
                            )
                        adapter = BestSellerProductAdapter(
                            requireContext(),
                            it,
                            this@UserFragment
                        )
                    }
                }
        }


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
        bundle.putString("startPoint", "User")
        findNavController().navigate(R.id.action_userFragment_to_detailsProductFragment, bundle)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {

            binding.txtUserInformation.id -> {
                findNavController().navigate(R.id.action_userFragment_to_userInforamtionFragment)
            }
            binding.txtAddress.id -> {
                findNavController().navigate(R.id.action_userFragment_to_addressFragment)
            }
            binding.txtAuth.id -> {

                requireActivity().startActivity(Intent(requireActivity(), AuthActivity::class.java))
                requireActivity().finish()
            }
            binding.txtLogout.id -> {

                requireActivity().startActivity(Intent(requireActivity(), AuthActivity::class.java))
                sharedPref!!.edit().apply {
                    putString("status", "logout").apply()
                    putString("name", null).apply()
                    putString("mobile", null).apply()
                    putString("id", null).apply()
                }
                requireActivity().finish()
            }
        }

    }

    fun selectedViews() {
        binding.txtUserInformation.setOnClickListener(this)
        binding.txtAddress.setOnClickListener(this)
        binding.txtAuth.setOnClickListener(this)
        binding.txtLogout.setOnClickListener(this)
    }
}