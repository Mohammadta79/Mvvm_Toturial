package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.AddressModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class AddressRepo  @Inject constructor(val apiInterfaceResult: ApiInterfaceResult) {

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
    ): Response<String>
            = apiInterfaceResult.addAddress(
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

    fun getAddress(id: String?): Single<ArrayList<AddressModel>>
            = apiInterfaceResult.getAddress(id)


    fun getCurrentAddress(id: String): Single<AddressModel> =
        apiInterfaceResult.getCurrentAddress(id)

}