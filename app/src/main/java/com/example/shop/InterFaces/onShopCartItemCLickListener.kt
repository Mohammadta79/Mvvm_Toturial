package com.example.shop.InterFaces

import com.example.shop.model.ShopCartModel

interface onShopCartItemCLickListener {
    fun onClick(shopCartModel: ShopCartModel)

    fun onChangeCount(order: String, product_id: String):HashMap<String,String>

}