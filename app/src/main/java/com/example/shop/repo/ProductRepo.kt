package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.CategoryModel
import com.example.shop.model.ProductModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class ProductRepo @Inject constructor(val apiInterfaceResult: ApiInterfaceResult) {

    suspend fun getCategory(): Response<ArrayList<CategoryModel>> = apiInterfaceResult.getCategory()


    fun bestSellers(): Single<ArrayList<ProductModel>> = apiInterfaceResult.bestSellers()


    fun getOffers(): Single<ArrayList<ProductModel>> = apiInterfaceResult.getOffers()

    fun getProductsCategory(category: String): Single<ArrayList<ProductModel>> =
        apiInterfaceResult.getProductsCategory(category)

}