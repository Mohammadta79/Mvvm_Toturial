package com.example.shop.view.bottomNavFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.adapter.ProductsListItemAdapter
import com.example.shop.data.LocalData
import com.example.shop.databinding.FragmentUserBinding
import com.example.shop.model.ProductModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFragment : Fragment(),onProductListItemClickListener,View.OnClickListener {
private lateinit var binding:FragmentUserBinding

@Inject
lateinit var bannerAdapter: BaseBannerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        selectedViews()

    }
  fun initView(){
      //init banner
        binding.Banner.setAdapter(bannerAdapter)

      //init adapter
      binding.userRecyclerView.apply {
          layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL,true)
          adapter = ProductsListItemAdapter(requireContext(),LocalData.productsItems(),this@UserFragment)
      }


    }

    override fun onHomeListItemClick(productModel: ProductModel) {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            binding.imgSetting.id->{

            }
            binding.txtUserInformation.id ->{

            }
            binding.txtAddress.id ->{

            }
        }

    }
    fun selectedViews(){
        binding.imgSetting.setOnClickListener(this)
        binding.txtUserInformation.setOnClickListener(this)
        binding.txtAddress.setOnClickListener ( this)
    }
}