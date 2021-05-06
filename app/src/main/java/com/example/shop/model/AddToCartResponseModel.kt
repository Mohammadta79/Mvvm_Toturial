package com.example.shop.model

data class AddToCartResponseModel(
    val count:String,
    val price: String,
    val status: String,
    val reminder:Int
)