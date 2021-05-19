package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.model.CategoryModel
import com.example.shop.model.ProductModel
import com.example.shop.repo.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(var repo: ProductRepo) : ViewModel() {

    private var categoryLD: MutableLiveData<ArrayList<CategoryModel>> = MutableLiveData()
    private var bestSellersLD: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    private var offersLD: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    private var productsCategoryLD: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun getCategory(): MutableLiveData<ArrayList<CategoryModel>> {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.getCategory()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    categoryLD.postValue(data)
                } else {
                    Log.e("categoryError", response.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return categoryLD
    }

    fun bestSellers(): MutableLiveData<ArrayList<ProductModel>> {

        compositeDisposable.add(
            repo.bestSellers()
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ProductModel?>?>() {
                    override fun onSuccess(t: List<ProductModel?>?) {
                        bestSellersLD.value = t as ArrayList<ProductModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.e("getMostCellError", e.toString())
                    }

                })
        )
        return bestSellersLD
    }

    fun getOffers(): MutableLiveData<ArrayList<ProductModel>> {

        compositeDisposable.add(
            repo.getOffers()
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ProductModel?>?>() {
                    override fun onSuccess(t: List<ProductModel?>?) {
                        offersLD.value = t as ArrayList<ProductModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.e("getMostCellError", e.toString())
                    }

                })
        )
        return offersLD
    }

    fun getProductsCategory(category: String): MutableLiveData<ArrayList<ProductModel>> {

        compositeDisposable.add(
            repo.getProductsCategory(category)
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ProductModel?>?>() {
                    override fun onSuccess(t: List<ProductModel?>?) {
                        productsCategoryLD.value = t as ArrayList<ProductModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.e("getMostCellError", e.toString())
                    }

                })
        )
        return productsCategoryLD
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}