package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.model.StringResponseModel
import com.example.shop.model.UserModel
import com.example.shop.repo.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(var repo: UserRepo) : ViewModel() {

    private var addInfoResponseLD: MutableLiveData<StringResponseModel> = MutableLiveData()
    private var getUserInfoLD:MutableLiveData<ArrayList<UserModel>> = MutableLiveData()
    fun addUserInfo(
        id: String,
        name: String,
        mobile: String,
        nationalID: String,
        email: String,
        phone: String,
        password:String
    ): MutableLiveData<StringResponseModel> {

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = repo.addInfo(id, name, mobile, nationalID, email, phone,password)
                    if (response.isSuccessful && response.body() != null) {
                        addInfoResponseLD.postValue(response.body())
                    } else {
                        Log.e("addInfoError", response.errorBody().toString())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        return addInfoResponseLD
    }

    fun getUserInfo(
        user_id:String
    ):MutableLiveData<ArrayList<UserModel>>{
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repo.getUserInfo(user_id)
                if (response.isSuccessful && response.body()!=null){
                    getUserInfoLD.postValue(response.body())
                }else{
                    Log.e("getInfoError", response.errorBody().toString() )
                }
            }catch (e:Exception){
             e.printStackTrace()
            }
        }
        return getUserInfoLD
    }

}