package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject


class MainRepo @Inject constructor(val apiInterfaceResult: ApiInterfaceResult) {



//    init {
//        apiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)
//    }

//    fun getCategory(): Single<ArrayList<CategoryModel>> {
//        return apiInterface!!.getCategory()
//    }
  suspend fun getCategory(): Response<ArrayList<CategoryModel>> {
            return apiInterfaceResult.getCategory()
    }
    fun bestSellers():Single<ArrayList<ProductModel>>{
        return apiInterfaceResult.bestSellers()
    }
    fun getOffers():Single<ArrayList<ProductModel>>{
        return apiInterfaceResult.getOffers()
    }
    fun getFavoriteProducts(id: String?): Single<ArrayList<ProductModel>> {
        return apiInterfaceResult.getFavoriteProducts(id)
    }

    fun getShopCarts(id: String?): Single<ArrayList<ShopCartModel>> {
        return apiInterfaceResult.getShopCarts(id)
    }

    fun getMyProducts(id: String?): Single<ArrayList<ProductModel>> {
        return apiInterfaceResult.getMyProducts(id)
    }

    fun getAddress(id: String?): Single<ArrayList<AddressModel>> {
        return apiInterfaceResult.getAddress(id)
    }

    fun getCurrentAddress(id: String): Single<AddressModel> {
        return apiInterfaceResult.getCurrentAddress(id)
    }


    suspend fun login(mobile: String, password: String): Response<AuthResponseModel> {
        return apiInterfaceResult.login(mobile, password)
    }

    suspend fun register(
        mobile: String,
        password: String,
        email: String
    ): Response<AuthResponseModel> {
        return apiInterfaceResult.register(mobile, password, email)
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
        return apiInterfaceResult.addAddress(
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
        return apiInterfaceResult.addInfo(id, name, mobile, nationalID, email, phone)
    }

    suspend fun setFavValue(id: String, fav: Int): Response<String> {
        return apiInterfaceResult.setFavValue(id, fav)
    }

    suspend fun manageShopCart(
        user_id: String,
        product_id: String,
        order: String
    ): Response<AddToCartResponseModel> {
        return apiInterfaceResult.manageShopCart(product_id, user_id, order)
    }
    suspend fun addToShopCart(
        user_id: Int,
        product_id: Int
    ): Response<CheckCartModel> {
        return apiInterfaceResult.addToShopCart(user_id, product_id)
    }

    suspend fun checkShopCart(
        user_id: Int,
        product_id: Int
    ): Response<CheckCartModel> {
        return apiInterfaceResult.checkShopCart(user_id, product_id)
    }

    suspend fun pay(id: String): Response<String> {
        return apiInterfaceResult.pay(id)
    }
}