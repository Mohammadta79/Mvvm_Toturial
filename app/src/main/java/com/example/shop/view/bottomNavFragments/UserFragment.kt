package com.example.shop.view.bottomNavFragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.adapter.ProductsListItemAdapter
import com.example.shop.data.LocalData
import com.example.shop.databinding.FragmentUserBinding
import com.example.shop.model.ProductModel
import com.example.shop.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : Fragment(), onProductListItemClickListener, View.OnClickListener {
    private lateinit var binding: FragmentUserBinding

    @Inject
    lateinit var bannerAdapter: BaseBannerAdapter
    private lateinit var userViewModel: UserViewModel
    private lateinit var liveData: MutableLiveData<ArrayList<ProductModel>>
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
        //init banner
        binding.Banner.setAdapter(bannerAdapter)

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        val sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        sharedPref!!.getString("id", null)?.let {
            liveData = userViewModel.getMyProductLiveData(it)
        }
        liveData.observe(requireActivity(), {
            binding.userRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
                adapter = ProductsListItemAdapter(
                    requireContext(),
                    it,
                    this@UserFragment
                )
            }
        })


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
        }

    }

    fun selectedViews() {
        binding.txtUserInformation.setOnClickListener(this)
        binding.txtAddress.setOnClickListener(this)
    }
}