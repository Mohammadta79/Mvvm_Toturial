package com.example.shop.api

import com.example.shop.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("getAllProducts.php")
    fun getAllProducts(): Single<ArrayList<CategoryModel>>

    @GET("getFavoriteProducts.php")
    fun getFavoriteProducts(@Field("id") id: String): Single<ArrayList<ProductModel>>

    @GET("getShopCart.php")
    fun getShopCarts(@Field("id") id: String): Single<ArrayList<ShopCartModel>>

    @GET("getMyProducts.php")
    fun getMyProducts(@Field("id") id: String): Single<ArrayList<ProductModel>>

    @GET("getAddress.php")
    fun getAddress(@Field("id") id: String): Single<ArrayList<AddressModel>>

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
        @Field("phone") phone: String,
    ): Response<String>

    @FormUrlEncoded
    @POST("setFav.php")
    suspend fun setFavValue(
        @Field("id") id: String,
        @Field("fav_value") favValue: Int
    ): Response<String>

    @FormUrlEncoded
    @POST("addToCart.php")
    suspend fun addToCart(
        @Field("product_id") product_id: String,
        @Field("user_id") user_id: String,
        @Field("order") order: String
    ): Response<AddToCartResponseModel>
}