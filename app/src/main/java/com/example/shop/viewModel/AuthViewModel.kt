package com.example.shop.viewModel


import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.repo.MainRepo
import com.example.shop.model.AuthResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    var authLoginData: MutableLiveData<AuthResponseModel> = MutableLiveData()
    private lateinit var apiService: MainRepo

    fun login(mobile: String, password: String): MutableLiveData<AuthResponseModel> {

        apiService = MainRepo()
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.login(mobile, password)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()
                authLoginData.value = data
            } else {
                Log.e("LoginError", response.errorBody().toString())
            }
        }
        return authLoginData
    }

    var authregisterData: MutableLiveData<AuthResponseModel> = MutableLiveData()
    fun register(
        mobile: String,
        password: String,
        email: String
    ): MutableLiveData<AuthResponseModel> {

        apiService = MainRepo()
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.register(mobile, password, email)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()
                authregisterData.value = data
            } else {
                Log.e("RegisterError", response.errorBody().toString())
            }
        }
        return authregisterData
    }


}