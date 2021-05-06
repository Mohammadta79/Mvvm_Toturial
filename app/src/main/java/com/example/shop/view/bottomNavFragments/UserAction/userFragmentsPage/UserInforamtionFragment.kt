package com.example.shop.view.bottomNavFragments.UserAction.userFragmentsPage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.shop.databinding.FragmentUserInforamtionBinding
import com.example.shop.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInforamtionFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentUserInforamtionBinding
    private val userViewModel by viewModels<UserViewModel>()
    private lateinit var response: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserInforamtionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedViews()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnAddInfo.id -> {
                if (checkInput()) {
                    val sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
                    sharedPref!!.getString("id", null)?.let {
                        response = userViewModel.addUserInfo(
                            it,
                            binding.edtName.text.toString(),
                            binding.edtMobile.text.toString(),
                            binding.edtNationalID.text.toString(),
                            binding.edtEmail.text.toString(),
                            binding.edtPhone.text.toString()
                        )
                        when (response) {
                            "ok" -> {
                                Toast.makeText(
                                    requireContext(),
                                    "اطلاعات با موفقیت ثبت شد",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(requireContext(), "خطا", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }


            }
        }
    }

    private fun selectedViews() {
        binding.btnAddInfo.setOnClickListener(this)
    }


    private fun checkInput(): Boolean {
        return (binding.edtEmail.text.toString().isNotEmpty() ||
                binding.edtMobile.text.toString().isNotEmpty() ||
                binding.edtName.text.toString().isNotEmpty() ||
                binding.edtNationalID.text.toString().isNotEmpty() ||
                binding.edtPassword.text.toString().isNotEmpty() ||
                binding.edtPhone.text.toString().isNotEmpty())
    }


}