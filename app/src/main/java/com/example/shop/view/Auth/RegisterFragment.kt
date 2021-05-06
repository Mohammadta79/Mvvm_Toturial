package com.example.shop.view.Auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.shop.databinding.FragmentRegisterBinding
import com.example.shop.view.MainActivity
import com.example.shop.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentRegisterBinding
    private  val authViewModel by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedViews()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnRegister.id -> {
                if (checkInput()) {
                    authViewModel.register(
                        binding.edtPhoneNumber.text.toString(),
                        binding.edtPassword.text.toString(),
                        binding.edtEmail.text.toString()
                    ).observe(requireActivity()) {
                        if (it != null && it.status == "ok") {
                            val sharedPref =
                                activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
                            sharedPref!!.edit().apply {
                                putString("name", it.name).apply()
                                putString("mobile", it.mobile).apply()
                                putString("id", it.id).apply()
                                putString("status", "login").apply()
                            }

                            requireActivity().startActivity(
                                Intent(
                                    requireActivity(),
                                    MainActivity::class.java
                                )
                            )
                            requireActivity().finish()
                        } else if (it.status == "exist") {
                            Toast.makeText(
                                requireContext(),
                                "این ایمیل یا شماره موبایل از قبل موجود می باشد",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "لطفا فیلدهای ستاره دار را به دقت وارد نمایید",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun checkInput(): Boolean {
        return !(binding.edtPassword.text.toString()
            .isEmpty() || binding.edtPhoneNumber.text.isEmpty() || binding.edtEmail.text.isEmpty())
    }



    private fun selectedViews() {
        binding.btnRegister.setOnClickListener(this)
    }

}