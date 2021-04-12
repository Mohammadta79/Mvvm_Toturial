package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.repo.MainRepo
import com.example.shop.model.AddressModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddressViewModel : ViewModel() {


    private var addressLiveData: MutableLiveData<ArrayList<AddressModel>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var apiService: MainRepo

    fun getAddressLiveData(id: String?): MutableLiveData<ArrayList<AddressModel>> {
        apiService = MainRepo()
        compositeDisposable.add(
            apiService.getAddress(id)
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<AddressModel?>?>() {

                    override fun onSuccess(t: List<AddressModel?>?) {
                        addressLiveData.value = t as ArrayList<AddressModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.e("AddressViewModelError", e.toString())
                    }


                })
        )
        return addressLiveData
    }

    private var currentAddressLiveData:MutableLiveData<AddressModel> = MutableLiveData()
    fun getCurrentAddress(id: String): MutableLiveData<AddressModel> {
        apiService = MainRepo()
        compositeDisposable.add(
            apiService.getCurrentAddress(id)
            !!.subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<AddressModel?>(){
                    override fun onSuccess(t: AddressModel?) {
                        currentAddressLiveData.value = t as AddressModel
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("CurrentAddresslError", e.toString())
                    }

                })
        )
        return currentAddressLiveData
    }


    private lateinit var addResponse: String
    fun addAddress(
        id: String,
        province: String,
        town: String,
        address: String,
        street: String,
        postalCode: String,
        plaque: String,
        reciverName: String,
        reciverPhone: String
    ): String {
        apiService = MainRepo()
        viewModelScope.launch(Dispatchers.Main) {
            val response = apiService.addAddress(
                id,
                province,
                town,
                address,
                street,
                postalCode,
                plaque,
                reciverName,
                reciverPhone
            )
            if (response.isSuccessful && response.body() != null) {
                addResponse = response.body()!!
            } else {
                Log.e("AddAddressError", response.errorBody().toString())
            }
        }
        return addResponse
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}