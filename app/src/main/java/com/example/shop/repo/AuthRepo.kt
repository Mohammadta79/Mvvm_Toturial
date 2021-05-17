package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.AuthResponseModel
import retrofit2.Response
import javax.inject.Inject

class AuthRepo  @Inject constructor(val apiInterfaceResult: ApiInterfaceResult) {

    suspend fun login(mobile: String, password: String): Response<AuthResponseModel>
            = apiInterfaceResult.login(mobile, password)


    suspend fun register(
        mobile: String,
        password: String,
        email: String
    ): Response<AuthResponseModel>
            = apiInterfaceResult.register(mobile, password, email)

}