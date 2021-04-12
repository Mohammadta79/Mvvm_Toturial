package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.repo.MainRepo
import com.example.shop.model.CategoryModel

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class ProductViewModel: ViewModel() {

    private var mutableLiveData: MutableLiveData<ArrayList<CategoryModel>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private lateinit var apiService: MainRepo

    fun getProductLiveData(): MutableLiveData<ArrayList<CategoryModel>> {
        apiService = MainRepo()
        compositeDisposable.add(
            apiService.getAllProducts()
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CategoryModel?>?>() {

                    override fun onSuccess(t: List<CategoryModel?>?) {
                        mutableLiveData.value = t as ArrayList<CategoryModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.d("ProductViewModelError", e.toString())
                    }


                })
        )
        return mutableLiveData
    }


    private var favLiveData: MutableLiveData<String> = MutableLiveData()
    fun setFav(id: String, fav: Int): MutableLiveData<String> {
        apiService = apiService
        viewModelScope.launch(Dispatchers.Main) {
            val response = apiService.setFavValue(id, fav)
            if (response.isSuccessful && response.body() != null) {
                favLiveData.value = response.body()
            } else {
                Log.e("setFavError", response.errorBody().toString())
            }
        }
        return favLiveData
    }

    private var bannerItemList: MutableLiveData<ArrayList<String>> = MutableLiveData()
    fun getBannerItem(): MutableLiveData<ArrayList<String>> {
        apiService = MainRepo()
        compositeDisposable.add(
            apiService.getProductBannerItem()
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