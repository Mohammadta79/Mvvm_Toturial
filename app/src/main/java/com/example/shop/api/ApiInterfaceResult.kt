package com.example.shop.api

import com.example.shop.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class ApiInterfaceResult @Inject constructor(var apiInterface: ApiInterface) {
    suspend fun getCategory(): Response<ArrayList<CategoryModel>> = apiInterface.getCategory()

    fun getOffers(): Single<ArrayList<ProductModel>> = apiInterface.getOffers()

    fun bestSellers(): Single<ArrayList<ProductModel>> = apiInterface.bestSellers()

    fun getFavoriteProducts(id: String?): Single<ArrayList<ProductModel>> =
        apiInterface.getFavoriteProducts(id)


    fun getShopCarts(id: String?): Single<ArrayList<ShopCartModel>> =
        apiInterface.getShopCarts(id)


    fun getMyProducts(id: String?): Single<ArrayList<ProductModel>> =
        apiInterface.getMyProducts(id)


    fun getAddress(id: String?): Single<ArrayList<AddressModel>> =
        apiInterface.getAddress(id)


    fun getCurrentAddress(id: String): Single<AddressModel> =
        apiInterface.getCurrentAddress(id)


    fun getOrders(id: String): Single<ArrayList<OrdersModel>> = apiInterface.getOrders(id)

    suspend fun login(mobile: String, password: String): Response<AuthResponseModel> =
        apiInterface!!.login(mobile, password)


    suspend fun register(
        mobile: String,
        password: String,
        email: String
    ): Response<AuthResponseModel> =
        apiInterface!!.register(mobile, password, email)


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
    ): Response<String> = apiInterface.AddAddress(
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


    suspend fun addInfo(
        id: String,
        name: String,
        mobile: String,
        nationalID: String,
        email: String,
        phone: String
    ): Response<String> =
        apiInterface.AddInfo(id, name, mobile, nationalID, email, phone)


    suspend fun setFavValue(id: String, fav: Int): Response<String> =
        apiInterface!!.setFavValue(id, fav)


    suspend fun manageShopCart(
        user_id: String,
        product_id: String,
        order: String
    ): Response<AddToCartResponseModel> = apiInterface.manageShopCart(product_id, user_id, order)

    suspend fun addToShopCart(
        user_id: Int,
        product_id: Int
    ): Response<CheckCartModel> = apiInterface.addToShopCart(user_id, product_id)

    suspend fun checkShopCart(
        user_id: Int,
        product_id: Int
    ): Response<CheckCartModel> = apiInterface.checkShopCart(user_id, product_id)

    suspend fun pay(id: String): Response<String> = apiInterface!!.pay(id)

}