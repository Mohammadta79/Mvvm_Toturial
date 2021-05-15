package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shop.model.OrdersModel
import com.example.shop.model.ProductModel
import com.example.shop.repo.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(var repo: MainRepo) : ViewModel() {

    private var ordersLD: MutableLiveData<ArrayList<OrdersModel>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getOrders(id: String): MutableLiveData<ArrayList<OrdersModel>> {
        compositeDisposable.add(
            repo.getOrders(id)
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<OrdersModel?>?>() {

                    override fun onSuccess(t: List<OrdersModel?>?) {
                        ordersLD.value = t as ArrayList<OrdersModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.d("ordersViewModel", e.toString())
                    }


                })
        )
        return ordersLD
    }


    override fun onCleared() {
        super.onCleared()
    }
}