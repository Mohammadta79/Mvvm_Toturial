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
import com.example.shop.databinding.FragmentUserInforamtionBinding
import com.example.shop.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInforamtionFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentUserInforamtionBinding
    private val userViewModel by viewModels<UserViewModel>()
    private var sharedPref: SharedPreferences? = null
    private lateinit var user_id: String
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
        initViews()
        selectedViews()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnAddInfo.id -> {
                if (checkInput()) {
                    user_id.let {
                        userViewModel.addUserInfo(
                            it,
                            binding.edtName.text.toString(),
                            binding.edtMobile.text.toString(),
                            binding.edtNationalID.text.toString(),
                            binding.edtEmail.text.toString(),
                            binding.edtPhone.text.toString(),
                            binding.edtPassword.text.toString()
                        ).observe(viewLifecycleOwner) {
                            when (it.status) {
                                "ok" -> {
                                    sharedPref!!.edit()
                                        .putString("name", binding.edtName.text.toString()).apply()
                                    Toast.makeText(
                                        requireContext(),
                                        "?????????????? ???? ???????????? ?????? ????",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    findNavController().navigate(R.id.action_userInforamtionFragment_to_userFragment)
                                }
                                else -> {
                                    Toast.makeText(requireContext(), "??????", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }

                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "???????? ?????????? ???????????? ???? ???? ?????? ???????? ????????????",
                        Toast.LENGTH_LONG
                    ).show()
                }


            }
        }
    }

    private fun selectedViews() {
        binding.btnAddInfo.setOnClickListener(this)
    }

    private fun initViews() {
        sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
        user_id = sharedPref!!.getString("id", null).toString()
        userViewModel.getUserInfo(user_id).observe(viewLifecycleOwner) {
            binding.edtEmail.setText(it[0].email)
            binding.edtMobile.setText(it[0].mobile)
            binding.edtName.setText(it[0].name)
            binding.edtNationalID.setText(it[0].national_id)
            binding.edtPassword.setText(it[0].password)
            binding.edtPhone.setText(it[0].phone)
        }
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