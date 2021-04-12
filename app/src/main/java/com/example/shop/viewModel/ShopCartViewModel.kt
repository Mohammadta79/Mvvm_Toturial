package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.api.ApiServices
import com.example.shop.model.AddToCartResponseModel
import com.example.shop.model.CategoryModel
import com.example.shop.model.ProductModel
import com.example.shop.model.ShopCartModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopCartViewModel : ViewModel() {


    private var mutableLiveData: MutableLiveData<ArrayList<ShopCartModel>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var apiService: ApiServices

    fun getShopCartLiveData(id: String): MutableLiveData<ArrayList<ShopCartModel>> {
        apiService = ApiServices()
        compositeDisposable.add(
            apiService.getShopCarts(id)
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ShopCartModel?>?>() {

                    override fun onSuccess(t: List<ShopCartModel?>?) {
                        mutableLiveData.value = t as ArrayList<ShopCartModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.e("ShopCartViewModelError", e.toString())
                    }


                })
        )
        return mutableLiveData
    }

    private var addTocartLiveData: MutableLiveData<AddToCartResponseModel> = MutableLiveData()
    fun addTocart(
        user_id: String,
        product_id: String,
        order: String
    ): MutableLiveData<AddToCartResponseModel> {
        apiService = ApiServices()
        viewModelScope.launch(Dispatchers.Main) {
            val response = apiService.addToCart(user_id, product_id, order)
            if (response.isSuccessful && response.body() != null) {
                addTocartLiveData.value = response.body()
            } else {
                Log.e("addToCartViewModelError", response.errorBody().toString())
            }
        }
        return addTocartLiveData
    }

    private var payResponse:MutableLiveData<String> = MutableLiveData()
    fun pay(id: String): MutableLiveData<String> {
        apiService = ApiServices()
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.pay(id)
            if (response.isSuccessful && response.body() != null) {
                payResponse.value = response.body()
            }else{
                Log.e("payError", response.errorBody().toString())
            }
        }
        return payResponse
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}