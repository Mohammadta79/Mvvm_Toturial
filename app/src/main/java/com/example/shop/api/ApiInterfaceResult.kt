package com.example.shop.api

import androidx.annotation.StringRes
import com.example.shop.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.FieldMap
import javax.inject.Inject

class ApiInterfaceResult @Inject constructor(var apiInterface: ApiInterface) {
    suspend fun getCategory(): Response<ArrayList<CategoryModel>> = apiInterface.getCategory()

    fun getOffers(): Single<ArrayList<ProductModel>> = apiInterface.getOffers()

    fun bestSellers(): Single<ArrayList<ProductModel>> = apiInterface.bestSellers()

    fun getFavoriteProducts(id: String?): Single<ArrayList<ProductModel>> =
        apiInterface.getFavoriteProducts(id)


    fun getShopCarts(id: String?): Single<ArrayList<ShopCartModel>> =
        apiInterface.getShopCarts(id)



    fun getAddress(id: String?): Single<ArrayList<AddressModel>> =
        apiInterface.getAddress(id)


    fun getCurrentAddress(id: String): Single<AddressModel> =
        apiInterface.getCurrentAddress(id)


    fun getOrders(id: String): Single<ArrayList<OrdersModel>> = apiInterface.getOrders(id)


    fun getProductsCategory(category:String):Single<ArrayList<ProductModel>> = apiInterface.getProductsCategory(category)

    suspend fun login(mobile: String, password: String): Response<AuthResponseModel> =
        apiInterface!!.login(mobile, password)


    suspend fun register(
        mobile: String,
        password: String,
        email: String
    ): Response<AuthResponseModel> =
        apiInterface!!.register(mobile, password, email)


     fun addAddress(
       params:HashMap<String,String>
    ): Single<StringResponseModel> = apiInterface.AddAddress(
       params
    )


    suspend fun addInfo(
        id: String,
        name: String,
        mobile: String,
        nationalID: String,
        email: String,
        phone: String,
        password: String
    ): Response<StringResponseModel> =
        apiInterface.AddInfo(id, name, mobile, nationalID, email, phone,password)


    suspend fun setFavValue(params: HashMap<String, String>): Response<StringResponseModel> =
        apiInterface.setFavValue(params)

    suspend fun getFavValue(params: HashMap<String, String>): Response<StringResponseModel> =
        apiInterface.getFavValue(params)


    suspend fun manageShopCart(
        user_id: String,
        product_id: String,
        order: String
    ): Response<ManageShopCartResponseModel> = apiInterface.manageShopCart(user_id, product_id, order)

    suspend fun addToShopCart(
        user_id: Int,
        product_id: Int
    ): Response<StringResponseModel> = apiInterface.addToShopCart(user_id, product_id)

    suspend fun checkShopCart(
        user_id: Int,
        product_id: Int
    ): Response<StringResponseModel> = apiInterface.checkShopCart(user_id, product_id)

    suspend fun getUserInfo(
        user_id: String
    ):Response<ArrayList<UserModel>> = apiInterface.getUserInfo(user_id)

    suspend fun pay(id: String): Response<String> = apiInterface.pay(id)

    suspend fun getCartPrice(id:String):Response<CartPriceModel> = apiInterface.getCartPrice(id)

    suspend fun deleteCart(product_id: String): Response<StringResponseModel> = apiInterface.deleteCart(product_id)
}