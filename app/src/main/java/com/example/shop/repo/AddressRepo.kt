package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.AddressModel
import com.example.shop.model.StringResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class AddressRepo @Inject constructor(val apiInterfaceResult: ApiInterfaceResult) {

    fun addAddress(
        params: HashMap<String, String>
    ): Single<StringResponseModel> = apiInterfaceResult.addAddress(
        params
    )

    fun getAddress(id: String?): Single<ArrayList<AddressModel>> = apiInterfaceResult.getAddress(id)


    fun getCurrentAddress(id: String): Single<AddressModel> =
        apiInterfaceResult.getCurrentAddress(id)

}