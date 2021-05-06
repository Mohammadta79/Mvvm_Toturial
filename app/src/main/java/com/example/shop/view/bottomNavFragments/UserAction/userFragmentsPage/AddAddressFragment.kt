package com.example.shop.view.bottomNavFragments.UserAction.userFragmentsPage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.shop.databinding.FragmentAddAddressBinding
import com.example.shop.viewModel.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAddressFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentAddAddressBinding
    lateinit var addressViewModel: AddressViewModel
    lateinit var response: String
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
                    val sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
                    sharedPref!!.getString("id", null)?.let {
                        response = addressViewModel.addAddress(
                            it,
                            binding.edtProvince.text.toString(),
                            binding.edtTown.text.toString(),
                            binding.edtAddress.text.toString(),
                            binding.edtPostalCode.text.toString(),
                            binding.edtPlaque.text.toString(),
                            binding.edtReciverName.text.toString(),
                            binding.edtStreet.text.toString(),
                            binding.edtReciverPhoneNumber.text.toString()
                        )
                    }

                    when (response) {
                        "ok" -> {
                            Toast.makeText(requireContext(), "آدرس جدید با موفقیت اضافه شد", Toast.LENGTH_SHORT)
                                .show()
                        }
                        "exist" -> {
                            Toast.makeText(requireContext(), "این ادرس از قبل موجود است", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else -> {
                            Toast.makeText(requireContext(), "خطا", Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(requireContext(), "لطفا تمامی فیلد ها را به دقت وارد نمایید", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initViews() {
        addressViewModel = ViewModelProvider(requireActivity()).get(AddressViewModel::class.java)

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

}