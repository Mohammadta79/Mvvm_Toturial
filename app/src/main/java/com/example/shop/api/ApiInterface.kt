package com.example.shop.api

import com.example.shop.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("getCategory.php")
    suspend fun getCategory(): Response<ArrayList<CategoryModel>>

    @GET("bestSellers.php")
    fun bestSellers(): Single<ArrayList<ProductModel>>

    @GET("getOffers.php")
    fun getOffers(): Single<ArrayList<ProductModel>>

    @GET("getFavorite.php")
    fun getFavoriteProducts(@Query("id") id: String?): Single<ArrayList<ProductModel>>


    @GET("getShopCart.php")
    fun getShopCarts(@Query("id") id: String?): Single<ArrayList<ShopCartModel>>


    @GET("getAddress.php")
    fun getAddress(@Query("id") id: String?): Single<ArrayList<AddressModel>>


    @GET("getCurrentAddress.php")
    fun getCurrentAddress(@Query("id") id: String): Single<AddressModel>

    @GET("getOrders.php")
    fun getOrders(@Query("id") id: String): Single<ArrayList<OrdersModel>>

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("mobile") mobile: String,
        @Field("password") password: String
    ): Response<AuthResponseModel>

    @FormUrlEncoded
    @POST("register.php")
    suspend fun register(
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("email") email: String
    ): Response<AuthResponseModel>

    @FormUrlEncoded
    @POST("addAddress.php")
    suspend fun AddAddress(
        @Field("id") id: String,
        @Field("province") province: String,
        @Field("town") town: String,
        @Field("address") address: String,
        @Field("street") street: String,
        @Field("postal_code") postalCode: String,
        @Field("plaque") plaque: String,
        @Field("reciver_name") reciverName: String,
        @Field("reciver_phone") reciverPhone: String
    ): Response<String>

    @FormUrlEncoded
    @POST("addInfo.php")
    suspend fun AddInfo(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("nationalID") nationalID: String,
        @Field("email") email: String,
        @Field("phone") phone: String
    ): Response<String>

    @FormUrlEncoded
    @POST("setFav.php")
    suspend fun setFavValue(
        @Field("id") id: String,
        @Field("fav_value") favValue: Int
    ): Response<String>

    @FormUrlEncoded
    @POST("manageShopCart.php")
    suspend fun manageShopCart(
        @Field("product_id") product_id: String,
        @Field("user_id") user_id: String,
        @Field("order") order: String
    ): Response<AddToCartResponseModel>

    @FormUrlEncoded
    @POST("addToShopCart.php")
    suspend fun addToShopCart(
        @Field("user_id") user_id: Int,
        @Field("product_id") product_id: Int

    ): Response<CheckCartModel>

    @FormUrlEncoded
    @POST("checkShopCart.php")
    suspend fun checkShopCart(
        @Field("user_id") user_id: Int,
        @Field("product_id") product_id: Int
    ): Response<CheckCartModel>

    @FormUrlEncoded
    @POST("pay.php")
    suspend fun pay(
        @Field("user_id") user_id: String
    ): Response<String>
}