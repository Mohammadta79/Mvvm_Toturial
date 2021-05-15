package com.example.shop.view.bottomNavFragments.UserAction.userFragmentsPage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.R
import com.example.shop.adapter.OrdersAdapter
import com.example.shop.databinding.FragmentOrdersBinding
import com.example.shop.model.OrdersModel
import com.example.shop.viewModel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private val viewModel by viewModels<OrdersViewModel>()
    var sharedPref: SharedPreferences? = null
    private lateinit var user_id: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

   fun initViews(){
       sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
       user_id = sharedPref!!.getString("id", null).toString()
       viewModel.getOrders(user_id).observe(viewLifecycleOwner){
           binding.orderRV.apply {
               adapter = OrdersAdapter(requireContext(), it)
               layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
           }
           if (it.isEmpty()){
               binding.txtNoOrder.visibility = View.VISIBLE
               binding.orderRV.visibility = View.GONE
           }else{
               binding.txtNoOrder.visibility = View.GONE
               binding.orderRV.visibility = View.VISIBLE
           }
       }



    }


}