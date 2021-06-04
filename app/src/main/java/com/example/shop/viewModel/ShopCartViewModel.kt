package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.model.*
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
class ShopCartViewModel @Inject constructor(var repo: ShopCartRepo) : ViewModel() {


    private var cartListLD: MutableLiveData<ArrayList<ShopCartModel>> = MutableLiveData()
    private var manageShopCartLD: MutableLiveData<ManageShopCartResponseModel> = MutableLiveData()
    private var addCartLD: MutableLiveData<StringResponseModel> = MutableLiveData()
    private var stringResponseLD: MutableLiveData<StringResponseModel> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var payResponse: MutableLiveData<String> = MutableLiveData()
    private var cartPrice: MutableLiveData<CartPriceModel> = MutableLiveData()
    private var deleteCartLD: MutableLiveData<StringResponseModel> = MutableLiveData()

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
    ):MutableLiveData<ManageShopCartResponseModel> {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.manageShopCart(user_id, product_id, order)
                if (response.isSuccessful && response.body() != null) {
                    manageShopCartLD.postValue(response.body())
                } else {
                    Log.e("manageCartVMError", response.errorBody().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
return manageShopCartLD
    }

    fun addToShopCart(
        user_id: Int,
        product_id: Int
    ): MutableLiveData<StringResponseModel> {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.addToShopCart(user_id, product_id)
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    addCartLD.postValue(data)
                } else {
                    Log.e("addToCartViewModelError", response.body().toString())
                }
            } catch (e: Exception) {
                Log.e("addToCartViewModelEx", e.toString())
            }

        }
        return addCartLD
    }

    fun checkShopCart(
        user_id: Int,
        product_id: Int
    ): MutableLiveData<StringResponseModel> {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.checkShopCart(user_id, product_id)
                if (response.isSuccessful && response.body() != null) {
                    stringResponseLD.postValue(response.body())
                } else {
                    Log.e("checkCartViewModelError", response.errorBody().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return stringResponseLD
    }


    fun pay(id: String): MutableLiveData<String> {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.pay(id)
                if (response.isSuccessful && response.body() != null) {
                    payResponse.postValue(response.body())
                } else {
                    Log.e("payError", response.errorBody().toString())
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return payResponse
    }

    fun getCartPrice(id: String): MutableLiveData<CartPriceModel> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.getCartPrice(id)
                if (response.isSuccessful && response.body() != null) {
                    cartPrice.postValue(response.body())
                } else {
                    Log.e("cartPriceError", response.errorBody().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return cartPrice
    }

    fun deleteCart(id: String): MutableLiveData<StringResponseModel> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.deleteCart(id)
                if (response.isSuccessful && response.body() != null) {
                    deleteCartLD.postValue(response.body())
                } else {
                    Log.e("deleteCartError", response.errorBody().toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return deleteCartLD
    }





    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}