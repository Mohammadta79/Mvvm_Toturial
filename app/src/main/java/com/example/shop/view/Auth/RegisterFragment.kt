package com.example.shop.view.Auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.shop.databinding.FragmentRegisterBinding
import com.example.shop.view.MainActivity
import com.example.shop.viewModel.AuthViewModel


class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var authViewModel: AuthViewModel
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
        initViews()
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
                    )
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
            .isEmpty() || binding.edtPhoneNumber.text.isEmpty())
    }

    private fun initViews() {
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        authViewModel.authregisterData.observe(requireActivity(), {
            if (it != null) {
                val sharedPref = activity?.getSharedPreferences("shp", Context.MODE_PRIVATE)
                sharedPref!!.edit().putString("name", it.name)
                sharedPref!!.edit().putString("mobile", it.mobile)
                sharedPref!!.edit().putString("id", it.id)
                sharedPref!!.edit().putString("status", "login")
                requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }
        })

    }

    private fun selectedViews() {
        binding.btnRegister.setOnClickListener(this)
    }

}