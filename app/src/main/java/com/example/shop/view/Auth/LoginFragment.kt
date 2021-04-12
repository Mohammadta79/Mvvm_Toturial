package com.example.shop.view.Auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.shop.databinding.FragmentLoginBinding
import com.example.shop.view.MainActivity
import com.example.shop.viewModel.AuthViewModel


class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        selectedViews()

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnLogin.id -> {
                if (checkInput()) {
                    authViewModel.login(
                        binding.edtPhoneNumber.text.toString(),
                        binding.edtPassword.text.toString()
                    ).observe(requireActivity()) {
                        if (it != null) {
                            val sharedPref =
                                activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
                            sharedPref!!.edit().apply {
                                putString("name", it.name)
                                putString("mobile", it.mobile)
                                putString("id", it.id)
                                putString("status", "login")
                            }

                            requireActivity().startActivity(
                                Intent(
                                    requireActivity(),
                                    MainActivity::class.java
                                )
                            )
                            requireActivity().finish()
                        }


                    }

                } else {
                    Toast.makeText(
                        requireContext(),
                        "لطفا تمامی فیلد ها را وارد نمایید",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun selectedViews() {
        binding.btnLogin.setOnClickListener(this)
    }

    private fun checkInput(): Boolean {
        return !(binding.edtPhoneNumber.text.toString()
            .isEmpty() || binding.edtPassword.text.toString().isEmpty())
    }

    private fun initViews() {

        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

    }


}