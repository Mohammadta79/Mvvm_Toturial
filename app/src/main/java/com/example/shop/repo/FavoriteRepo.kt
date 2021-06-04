package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.ProductModel
import com.example.shop.model.StringResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class FavoriteRepo @Inject constructor(val apiInterfaceResult: ApiInterfaceResult) {

    fun getFavoriteProducts(id: String?): Single<ArrayList<ProductModel>>
            = apiInterfaceResult.getFavoriteProducts(id)


    suspend fun setFavValue(params: HashMap<String, String>): Response<StringResponseModel> =
        apiInterfaceResult.setFavValue(params)

    suspend fun getFavValue(params: HashMap<String, String>): Response<StringResponseModel> =
        apiInterfaceResult.getFavValue(params)
}