package com.example.shop.view.bottomNavFragments.UserAction.userFragmentsPage

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
import com.example.shop.R
import com.example.shop.adapter.AddressAdapter
import com.example.shop.databinding.FragmentAddressBinding
import com.example.shop.model.AddressModel
import com.example.shop.viewModel.AddressViewModel

class AddressFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAddressBinding
    lateinit var addressViewModel: AddressViewModel
    lateinit var liveData: MutableLiveData<ArrayList<AddressModel>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        selectedViews()

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.txtAddAddress.id -> {
                findNavController().navigate(R.id.action_addressFragment_to_addAddressFragment)
            }
        }
    }

    fun initViews() {

        addressViewModel = ViewModelProvider(requireActivity()).get(AddressViewModel::class.java)
        val sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        sharedPref!!.getString("id", null)?.let {
            liveData = addressViewModel.getAddressLiveData(it)
        }
        liveData.observe(requireActivity(), {
            binding.addressRecycler.apply {
                adapter = AddressAdapter(requireContext(), it)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        })


    }

    fun selectedViews() {
        binding.txtAddAddress.setOnClickListener(this)
    }


}