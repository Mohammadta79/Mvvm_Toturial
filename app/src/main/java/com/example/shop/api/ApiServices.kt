package com.example.shop.api

import com.example.shop.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Field


class ApiServices() {

    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)
    }

    fun getAllProducts(): Single<ArrayList<CategoryModel>> {
        return apiInterface!!.getAllProducts()
    }

    fun getFavoriteProducts(id: String): Single<ArrayList<ProductModel>> {
        return apiInterface!!.getFavoriteProducts(id)
    }

    fun getShopCarts(id: String): Single<ArrayList<ShopCartModel>> {
        return apiInterface!!.getShopCarts(id)
    }

    fun getMyProducts(id: String): Single<ArrayList<ProductModel>> {
        return apiInterface!!.getMyProducts(id)
    }

    fun getAddress(id: String): Single<ArrayList<AddressModel>> {
        return apiInterface!!.getAddress(id)
    }
    fun getCurrentAddress(id: String): Single<AddressModel> {
        return apiInterface!!.getCurrentAddress(id)
    }


    suspend fun login(mobile: String, password: String): Response<AuthResponseModel> {
        return apiInterface!!.login(mobile, password)
    }

    suspend fun register(
        mobile: String,
        password: String,
        email: String
    ): Response<AuthResponseModel> {
        return apiInterface!!.register(mobile, password, email)
    }

    suspend fun addAddress(
        id: String,
        province: String,
        town: String,
        address: String,
        street: String,
        postalCode: String,
        plaque: String,
        reciverName: String,
        reciverPhone: String
    ): Response<String> {
        return apiInterface!!.AddAddress(
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
    }

    suspend fun addInfo(
        id: String,
        name: String,
        mobile: String,
        nationalID: String,
        email: String,
        phone: String
    ): Response<String> {
        return apiInterface!!.AddInfo(id, name, mobile, nationalID, email, phone)
    }

    suspend fun setFavValue(id: String, fav: Int): Response<String> {
        return apiInterface!!.setFavValue(id, fav)
    }

    suspend fun addToCart(
        user_id: String,
        product_id: String,
        order: String
    ): Response<AddToCartResponseModel> {
        return apiInterface!!.addToCart(product_id, user_id, order)
    }
    suspend fun pay(id: String):Response<String>{
        return apiInterface!!.pay(id)
    }
}