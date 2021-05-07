package com.example.shop.data

object LocalData {

    fun homeSliderItems(): List<String> {
        val urls: MutableList<String> = ArrayList()
        urls.add("http://mohammadta79.ir/shop/images/2.jpg")
        urls.add("http://mohammadta79.ir/shop/images/3.jpg")
        urls.add("http://mohammadta79.ir/shop/images/4.jpg")
        urls.add("http://mohammadta79.ir/shop/images/5.jpg")
        return urls
    }
    fun userSliderItems(): List<String> {
        val urls: MutableList<String> = ArrayList()
        urls.add("http://mohammadta79.ir/shop/images/6.jpg")
        urls.add("http://mohammadta79.ir/shop/images/7.jpg")
        urls.add("http://mohammadta79.ir/shop/images/8.jpg")
        urls.add("http://mohammadta79.ir/shop/images/2.jpg")
        return urls
    }
}