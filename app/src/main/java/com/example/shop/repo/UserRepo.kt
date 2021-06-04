package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.StringResponseModel
import com.example.shop.model.UserModel
import retrofit2.Response
import javax.inject.Inject

class UserRepo @Inject constructor(val apiInterfaceResult: ApiInterfaceResult){



    suspend fun addInfo(
        id: String,
        name: String,
        mobile: String,
        nationalID: String,
        email: String,
        phone: String,
        password:String
    ): Response<StringResponseModel>
            = apiInterfaceResult.addInfo(id, name, mobile, nationalID, email, phone,password)


    suspend fun getUserInfo(
        user_id:String
    ):Response<ArrayList<UserModel>> = apiInterfaceResult.getUserInfo(user_id)

}