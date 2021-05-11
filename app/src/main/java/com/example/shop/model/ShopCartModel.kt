package com.example.shop.model

data class ShopCartModel(
    var idproduct: String,
    var name: String,
    var image: String,
    var price: String,
    var category: String,
    var count: Int,
    var reminder: Int,
    var describtion: String,
    var weight: String,
    var favorite: Int
)