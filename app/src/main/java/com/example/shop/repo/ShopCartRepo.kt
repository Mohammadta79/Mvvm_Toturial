package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.AddToCartResponseModel
import com.example.shop.model.CheckCartModel
import com.example.shop.model.ShopCartModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class ShopCartRepo @Inject constructor(val apiInterfaceResult: ApiInterfaceResult) {
    fun getShopCarts(id: String?): Single<ArrayList<ShopCartModel>>
            = apiInterfaceResult.getShopCarts(id)

    suspend fun manageShopCart(
        user_id: String,
        product_id: String,
        order: String
    ): Response<AddToCartResponseModel>
            = apiInterfaceResult.manageShopCart(product_id, user_id, order)


    suspend fun addToShopCart(
        user_id: Int,
        product_id: Int
    ): Response<CheckCartModel>
            = apiInterfaceResult.addToShopCart(user_id, product_id)


    suspend fun checkShopCart(
        user_id: Int,
        product_id: Int
    ): Response<CheckCartModel>
            = apiInterfaceResult.checkShopCart(user_id, product_id)


    suspend fun pay(id: String): Response<String>
            = apiInterfaceResult.pay(id)

}