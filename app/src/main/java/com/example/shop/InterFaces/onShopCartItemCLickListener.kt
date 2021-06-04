package com.example.shop.InterFaces

import androidx.lifecycle.MutableLiveData
import com.example.shop.model.ManageShopCartResponseModel

import com.example.shop.model.ShopCartModel

interface onShopCartItemCLickListener {
    fun onClick(shopCartModel: ShopCartModel)

    fun onChangeCount(order: String, product_id: String):MutableLiveData<ManageShopCartResponseModel>

    fun deleteCart(product_id:String)
}