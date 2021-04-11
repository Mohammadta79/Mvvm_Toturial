package com.example.shop.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


 class ApiClient {


     companion object{
        private val baseUrl = "http://192.168.1.43/shop/"
        private var retrofit: Retrofit? = null
         fun  getClient(): Retrofit? {
             if (retrofit == null) {
                 retrofit = Retrofit.Builder()
                     .addConverterFactory(GsonConverterFactory.create())
                     .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                     .baseUrl(baseUrl)
                     .build()
             }
             return retrofit
         }
     }



}