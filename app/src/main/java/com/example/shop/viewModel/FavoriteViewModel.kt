package com.example.shop.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.shop.model.ProductModel
import com.example.shop.repo.FavoriteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var repo: FavoriteRepo) : ViewModel() {


    private var favoriteLD: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var favLD: MutableLiveData<String> = MutableLiveData()


    fun getFavoriteLiveData(id: String?): MutableLiveData<ArrayList<ProductModel>> {

        compositeDisposable.add(
            repo.getFavoriteProducts(id)
            !!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<ProductModel?>?>() {

                    override fun onSuccess(t: List<ProductModel?>?) {
                        favoriteLD.value = t as ArrayList<ProductModel>?
                    }

                    override fun onError(e: @io.reactivex.rxjava3.annotations.NonNull Throwable?) {
                        Log.d("FavoriteViewModelError", e.toString())
                    }


                })
        )
        return favoriteLD
    }


    fun setFav(id: String, fav: Int): MutableLiveData<String> {
        viewModelScope.launch(Dispatchers.Main) {
            val response = repo.setFavValue(id, fav)
            if (response.isSuccessful && response.body() != null) {
                favLD.value = response.body()
            } else {
                Log.e("setFavError", response.errorBody().toString())
            }
        }
        return favLD
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}