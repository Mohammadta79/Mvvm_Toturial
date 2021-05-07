package com.example.shop.model

data class ProductModel(
    var id:String,
    var name: String,
    var image: String,
    var price: String,
    var describtion: String,
    var category: String,
    var weight: String,
    var reminder:Int,
    var offer:Int
)