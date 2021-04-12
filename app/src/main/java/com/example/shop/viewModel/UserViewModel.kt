package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.repo.MainRepo
import com.example.shop.model.ProductModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {


    private var mutableLiveData: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var apiService: MainRepo

    fun getMyProductLiveData(id: String?): MutableLiveData<ArrayList<ProductModel>> {
        apiService = MainRepo()
        compositeDisposable.add(
            apiService.getMyProducts(id)
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ProductModel?>?>() {

                    override fun onSuccess(t: List<ProductModel?>?) {
                        mutableLiveData.value = t as ArrayList<ProductModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.d("FavoriteViewModelError", e.toString())
                    }


                })
        )
        return mutableLiveData
    }

    private lateinit var addInfoResponse: String
    fun addUserInfo(
        id: String,
        name: String,
        mobile: String,
        nationalID: String,
        email: String,
        phone: String
    ): String {
        apiService = MainRepo()
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.addInfo(id, name, mobile, nationalID, email, phone)
            if (response.isSuccessful && response.body() != null){
                addInfoResponse = response.body()!!
            }else{
                Log.e("addInfoError", response.errorBody().toString() )
            }
        }
        return addInfoResponse
    }


    private var bannerItemList: MutableLiveData<ArrayList<String>> = MutableLiveData()
    fun getBannerItem(): MutableLiveData<ArrayList<String>> {
        apiService = MainRepo()
        compositeDisposable.add(
            apiService.getUserBannerItem()
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<String?>?>() {

                    override fun onSuccess(t: List<String?>?) {
                        bannerItemList.value = t as ArrayList<String>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.d("getBannerError", e.toString())
                    }


                })
        )
        return bannerItemList
    }
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}