package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import retrofit2.Response
import javax.inject.Inject

class UserRepo @Inject constructor(val apiInterfaceResult: ApiInterfaceResult){



    suspend fun addInfo(
        id: String,
        name: String,
        mobile: String,
        nationalID: String,
        email: String,
        phone: String
    ): Response<String>
            = apiInterfaceResult.addInfo(id, name, mobile, nationalID, email, phone)



}