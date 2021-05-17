package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(var repo: UserRepo) : ViewModel() {


    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


    private lateinit var addInfoResponse: String
    fun addUserInfo(
        id: String,
        name: String,
        mobile: String,
        nationalID: String,
        email: String,
        phone: String
    ): String {

        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.addInfo(id, name, mobile, nationalID, email, phone)
            if (response.isSuccessful && response.body() != null){
                addInfoResponse = response.body()!!
            }else{
                Log.e("addInfoError", response.errorBody().toString() )
            }
        }
        return addInfoResponse
    }



    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}