package com.example.shop.model

data class ShopCartModel(
    var name: String,
    var image: String,
    var price: String,
    var category: String,
    var count: Int,
    var reminder:Int
)