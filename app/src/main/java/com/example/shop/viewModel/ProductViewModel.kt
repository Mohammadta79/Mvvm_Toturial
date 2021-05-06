package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.repo.MainRepo
import com.example.shop.model.CategoryModel
import com.example.shop.model.OfferProductModel
import com.example.shop.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(var repo: MainRepo): ViewModel() {

    private var categorymutableLiveData: MutableLiveData<ArrayList<CategoryModel>> = MutableLiveData()
    private var mostCellmutableLiveData: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    private var offersmutableLiveData: MutableLiveData<ArrayList<OfferProductModel>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


//    fun getCategory(): MutableLiveData<ArrayList<CategoryModel>> {
//        apiService = MainRepo()
//        compositeDisposable.add(
//            apiService.getCategory()
//            !!.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object : DisposableSingleObserver<List<CategoryModel?>?>() {
//
//                    override fun onSuccess(t: List<CategoryModel?>?) {
//                        categorymutableLiveData.value = t as ArrayList<CategoryModel>?
//                    }
//
//                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
//                        Log.e("getCategoryError", e.toString())
//                    }
//
//
//                })
//        )
//        return categorymutableLiveData
//    }
    fun getCategory(): MutableLiveData<ArrayList<CategoryModel>> {

    viewModelScope.launch(Dispatchers.IO){
        try {
            val response = repo.getCategory()
            if  (response.isSuccessful && response.body() != null) {
                val data = response.body()
                categorymutableLiveData.postValue(data)
            } else {
                Log.e("categoryError", response.toString())
            }
        }catch (e:Exception){
           e.printStackTrace()
        }
    }
    return categorymutableLiveData
}

    fun bestSellers(): MutableLiveData<ArrayList<ProductModel>> {

        compositeDisposable.add(
            repo.bestSellers()
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ProductModel?>?>() {
                    override fun onSuccess(t: List<ProductModel?>?) {
                        mostCellmutableLiveData.value = t as ArrayList<ProductModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.e("getMostCellError", e.toString())
                    }

                })
        )
        return mostCellmutableLiveData
    }

    fun getOffers(): MutableLiveData<ArrayList<OfferProductModel>> {

        compositeDisposable.add(
            repo.getOffers()
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<OfferProductModel?>?>() {
                    override fun onSuccess(t: List<OfferProductModel?>?) {
                        offersmutableLiveData.value = t as ArrayList<OfferProductModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.e("getMostCellError", e.toString())
                    }

                })
        )
        return offersmutableLiveData
    }

//TODO: this func most go to new view model
    private var favLiveData: MutableLiveData<String> = MutableLiveData()
    fun setFav(id: String, fav: Int): MutableLiveData<String> {
        viewModelScope.launch(Dispatchers.Main) {
            val response = repo.setFavValue(id, fav)
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

        compositeDisposable.add(
            repo.getProductBannerItem()
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