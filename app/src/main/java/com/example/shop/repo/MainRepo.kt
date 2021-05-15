package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject


class MainRepo @Inject constructor(val apiInterfaceResult: ApiInterfaceResult) {


    suspend fun getCategory(): Response<ArrayList<CategoryModel>>
        = apiInterfaceResult.getCategory()


    fun bestSellers(): Single<ArrayList<ProductModel>>
        = apiInterfaceResult.bestSellers()


    fun getOffers(): Single<ArrayList<ProductModel>>
        = apiInterfaceResult.getOffers()


    fun getFavoriteProducts(id: String?): Single<ArrayList<ProductModel>>
        = apiInterfaceResult.getFavoriteProducts(id)


    fun getShopCarts(id: String?): Single<ArrayList<ShopCartModel>>
        = apiInterfaceResult.getShopCarts(id)


    fun getMyProducts(id: String?): Single<ArrayList<ProductModel>>
        = apiInterfaceResult.getMyProducts(id)


    fun getAddress(id: String?): Single<ArrayList<AddressModel>>
        = apiInterfaceResult.getAddress(id)


    fun getCurrentAddress(id: String): Single<AddressModel> =
        apiInterfaceResult.getCurrentAddress(id)


    fun getOrders(id: String): Single<ArrayList<OrdersModel>> = apiInterfaceResult.getOrders(id)

    suspend fun login(mobile: String, password: String): Response<AuthResponseModel>
        = apiInterfaceResult.login(mobile, password)


    suspend fun register(
        mobile: String,
        password: String,
        email: String
    ): Response<AuthResponseModel>
        = apiInterfaceResult.register(mobile, password, email)


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
    ): Response<String>
        = apiInterfaceResult.addAddress(
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
    ): Response<String>
        = apiInterfaceResult.addInfo(id, name, mobile, nationalID, email, phone)


    suspend fun setFavValue(id: String, fav: Int): Response<String> =
         apiInterfaceResult.setFavValue(id, fav)


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