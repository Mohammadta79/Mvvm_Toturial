package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.model.CheckCartModel
import com.example.shop.model.AddToCartResponseModel
import com.example.shop.model.ShopCartModel
import com.example.shop.repo.ShopCartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopCartViewModel @Inject constructor(var repo : ShopCartRepo) : ViewModel() {


    private var cartListLD: MutableLiveData<ArrayList<ShopCartModel>> = MutableLiveData()
    private var manageCartLD: MutableLiveData<AddToCartResponseModel> = MutableLiveData()
    private var addCartLD: MutableLiveData<CheckCartModel> = MutableLiveData()
    private var checkCartLD: MutableLiveData<CheckCartModel> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun getShopCartLiveData(id: String?): MutableLiveData<ArrayList<ShopCartModel>> {

        compositeDisposable.add(
            repo.getShopCarts(id)
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ShopCartModel?>?>() {

                    override fun onSuccess(t: List<ShopCartModel?>?) {
                        cartListLD.value = t as ArrayList<ShopCartModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.e("ShopCartViewModelError", e.toString())
                    }


                })
        )
        return cartListLD
    }


    fun manageShopCart(
        user_id: String,
        product_id: String,
        order: String
    ): MutableLiveData<AddToCartResponseModel> {

        viewModelScope.launch(Dispatchers.Main) {
            try{
                val response = repo.manageShopCart(user_id, product_id, order)
                if (response.isSuccessful && response.body() != null) {
                    manageCartLD.value = response.body()
                } else {
                    Log.e("manageCartVMError", response.errorBody().toString())
                }
            }catch (e:Exception){
                e.printStackTrace()
            }

        }
        return manageCartLD
    }

    fun addToShopCart(
        user_id: Int,
        product_id: Int
    ): MutableLiveData<CheckCartModel> {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.addToShopCart(user_id, product_id)
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    addCartLD.postValue(data)
                } else {
                    Log.e("addToCartViewModelError", response.body().toString())
                }
            }catch (e:Exception){
             Log.e("addToCartViewModelEx", e.toString())
            }

        }
        return addCartLD
    }

    fun checkShopCart(
        user_id: Int,
        product_id: Int
    ): MutableLiveData<CheckCartModel> {

        viewModelScope.launch(Dispatchers.Main) {
            val response = repo.checkShopCart(user_id, product_id)
            if (response.isSuccessful && response.body() != null) {
                checkCartLD.value = response.body()
            } else {
                Log.e("checkCartViewModelError", response.errorBody().toString())
            }
        }
        return checkCartLD
    }


    private var payResponse:MutableLiveData<String> = MutableLiveData()
    fun pay(id: String): MutableLiveData<String> {

        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.pay(id)
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