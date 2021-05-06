package com.example.shop.model

data class ShopCartModel(
    var id: String,
    var name: String,
    var image: String,
    var price: String,
    var category: String,
    var count: Int,
    var reminder: Int,
    var desc: String,
    var weight: String,
    var favorite: Int
)