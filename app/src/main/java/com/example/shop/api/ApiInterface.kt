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

    @GET("getProductsCategory.php")
    fun getProductsCategory(@Query("category") category: String): Single<ArrayList<ProductModel>>

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
    fun AddAddress(
        @FieldMap params: HashMap<String, String>
    ): Single<StringResponseModel>

    @FormUrlEncoded
    @POST("addUserInfo.php")
    suspend fun AddInfo(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("nationalID") nationalID: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Response<StringResponseModel>

    @FormUrlEncoded
    @POST("setFav.php")
    suspend fun setFavValue(
        @Field("id") id: String,
        @Field("fav_value") favValue: Int
    ): Response<String>


    @GET("manageShopCart.php")
    suspend fun manageShopCart(
        @Query("user_id") user_id: String,
        @Query("product_id") product_id: String,
        @Query("order") order: String
    ): Response<ManageCartResponseModel>

    @FormUrlEncoded
    @POST("addToShopCart.php")
    suspend fun addToShopCart(
        @Field("user_id") user_id: Int,
        @Field("product_id") product_id: Int

    ): Response<StringResponseModel>

    @FormUrlEncoded
    @POST("checkShopCart.php")
    suspend fun checkShopCart(
        @Field("user_id") user_id: Int,
        @Field("product_id") product_id: Int
    ): Response<StringResponseModel>

    @FormUrlEncoded
    @POST("getUserInfo.php")
    suspend fun getUserInfo(
        @Field("user_id") user_id: String
    ):Response<ArrayList<UserModel>>

    @FormUrlEncoded
    @POST("pay.php")
    suspend fun pay(
        @Field("user_id") user_id: String
    ): Response<String>

    @FormUrlEncoded
    @POST("getCartPrice.php")
    suspend fun getCartPrice(
        @Field("id") id:String
    ):Response<CartPriceModel>

}