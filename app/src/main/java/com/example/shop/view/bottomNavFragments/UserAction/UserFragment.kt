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
import com.example.shop.adapter.ProductAdapter
import com.example.shop.databinding.FragmentUserBinding
import com.example.shop.model.ProductModel
import com.example.shop.view.Auth.AuthActivity
import com.example.shop.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class UserFragment : Fragment(), View.OnClickListener {

    @Inject
    @Named("UserFragment")
    lateinit var bannerAdapter: BaseBannerAdapter
    private lateinit var binding: FragmentUserBinding
    private lateinit var user_id: String
    private lateinit var user_status: String
    private lateinit var user_name: String
    private lateinit var user_mobile: String
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
        user_status = sharedPref!!.getString("status", "logout").toString()
        user_name = sharedPref!!.getString("name", "").toString()
        user_mobile = sharedPref!!.getString("mobile", "").toString()
        user_id = sharedPref!!.getString("id", null).toString()
        when (user_status) {
            "login" -> {
                binding.orginalLayout.visibility = View.VISIBLE
                binding.registerLayout.visibility = View.GONE
            }
            "logout" -> {
                binding.orginalLayout.visibility = View.GONE
                binding.registerLayout.visibility = View.VISIBLE
            }
        }
        binding.txtPhoneNumber.text = user_mobile
        binding.txtUsername.text = user_name

        //set slider adapter
        binding.Banner.setAdapter(bannerAdapter)


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
            binding.txtOrders.id -> {
                findNavController().navigate(R.id.action_userFragment_to_ordersFragment)
            }
        }


    }

    fun selectedViews() {
        binding.txtUserInformation.setOnClickListener(this)
        binding.txtAddress.setOnClickListener(this)
        binding.txtAuth.setOnClickListener(this)
        binding.txtLogout.setOnClickListener(this)
        binding.txtOrders.setOnClickListener(this)
    }
}