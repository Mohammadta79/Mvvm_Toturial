package com.example.shop.model

data class OrdersModel(
    var price:String,
    var paycode:String,
    var image:String,
    var name:String,
    var province:String,
    var city:String,
    var count:Int
)