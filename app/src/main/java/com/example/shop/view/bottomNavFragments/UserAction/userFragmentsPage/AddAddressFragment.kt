package com.example.shop.view.bottomNavFragments.UserAction.userFragmentsPage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shop.R
import com.example.shop.databinding.FragmentAddAddressBinding
import com.example.shop.viewModel.AddressViewModel
import com.example.shop.viewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAddressFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentAddAddressBinding
    private val addressViewModel by viewModels<AddressViewModel>()
    private lateinit var user_id: String
    var sharedPref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedViews()
        initViews()
    }

    private fun selectedViews() {
        binding.btnAddAddress.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnAddAddress.id -> {
                if (checkInput()) {
                    user_id.let {
                        addressViewModel.addAddress(
                            getParams()
                        ).observe(viewLifecycleOwner) {
                            when (it.status) {
                                "ok" -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "آدرس جدید با موفقیت اضافه شد",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    findNavController().navigate(R.id.action_addAddressFragment_to_addressFragment)
                                }
                                else -> {
                                    Toast.makeText(requireContext(), "خطایی رخ داده است", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    }


                } else {
                    Toast.makeText(
                        requireContext(),
                        "لطفا تمامی فیلد ها را به دقت وارد نمایید",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun checkInput(): Boolean {
        return !(binding.edtProvince.text.toString().isEmpty() ||
                binding.edtTown.text.toString().isEmpty() ||
                binding.edtAddress.text.toString().isEmpty() ||
                binding.edtPostalCode.text.toString().isEmpty() ||
                binding.edtPlaque.text.toString().isEmpty() ||
                binding.edtReciverName.text.toString().isEmpty() ||
                binding.edtStreet.text.toString().isEmpty() ||
                binding.edtReciverPhoneNumber.text.toString().isEmpty()
                )
    }

    private fun initViews() {
        sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        user_id = sharedPref!!.getString("id", null).toString()
    }

    private fun getParams(): HashMap<String, String> {
        var params: HashMap<String, String> = HashMap()
        params.put("user_id", user_id)
        params.put("province", binding.edtProvince.text.toString())
        params.put("city", binding.edtTown.text.toString())
        params.put("address", binding.edtAddress.text.toString())
        params.put("street", binding.edtStreet.text.toString())
        params.put("postal_code", binding.edtPostalCode.text.toString())
        params.put("plaque", binding.edtPlaque.text.toString())
        params.put("reciver", binding.edtReciverName.text.toString())
        params.put("mobile", binding.edtReciverPhoneNumber.text.toString())
        return params
    }


}