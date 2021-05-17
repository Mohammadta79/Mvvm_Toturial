package com.example.shop.repo

import com.example.shop.api.ApiInterfaceResult
import com.example.shop.model.OrdersModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class OrdersRepo @Inject constructor(val apiInterfaceResult: ApiInterfaceResult) {

    fun getOrders(id: String): Single<ArrayList<OrdersModel>> = apiInterfaceResult.getOrders(id)
}