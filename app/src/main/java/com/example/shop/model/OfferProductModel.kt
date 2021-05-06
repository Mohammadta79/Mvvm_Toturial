package com.example.shop.model

data class OfferProductModel(
    var id:String,
    var name: String,
    var image: String,
    var price: String,
    var describtion: String,
    var category: String,
    var weight: String,
    var favorite:Int,
    var reminder:Int,
    var offer:String
)