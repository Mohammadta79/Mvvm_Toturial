package com.example.shop.viewModel


import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.repo.MainRepo
import com.example.shop.model.AuthResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(var repo : MainRepo) : ViewModel() {
  private var authLoginData: MutableLiveData<AuthResponseModel> = MutableLiveData()


    fun login(mobile: String, password: String): MutableLiveData<AuthResponseModel> {


        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.login(mobile, password)
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    authLoginData.postValue(data)
                } else {
                    Log.e("LoginError", response.errorBody().toString())
                }
            } catch (e: Exception) {
                Log.e("LoginException", e.toString())
            }

        }
        return authLoginData
    }

   private var authregisterData: MutableLiveData<AuthResponseModel> = MutableLiveData()
    fun register(
        mobile: String,
        password: String,
        email: String
    ): MutableLiveData<AuthResponseModel> {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.register(mobile, password, email)
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    authregisterData.postValue(data)
                } else {
                    Log.e("RegisterError", response.errorBody().toString())
                }
            } catch (e: Exception) {
                Log.e("RegisterException", e.toString())
            }

        }
        return authregisterData
    }


}