package com.example.shop.model

data class ProductModel(
    var id:String,
    var name: String,
    var image: String,
    var price: String,
    var desc: String,
    var category: String,
    var weight: String,
    var favorite:Int,
    var reminder:Int
)