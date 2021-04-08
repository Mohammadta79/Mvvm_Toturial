package com.example.shop.data

import com.example.shop.model.ProductModel

object LocalData {

    fun homeSliderItems(): List<String> {
        val urls: MutableList<String> = ArrayList()
        urls.add("https://media.wired.com/photos/5c9040ee4950d24718d6da99/16:9/w_2400,h_1350,c_limit/shoppingcart-1066110386.jpg")
        urls.add("https://www.incimages.com/uploaded_files/image/1920x1080/getty_663974538_353364.jpg")
        urls.add("http://www.visitgreece.gr/deployedFiles/StaticFiles/Photos/Generic%20Contents/Forests/mountains_2_560.jpg")
        return urls
    }
    fun herbal_medicine_Products(): List<ProductModel> {
        val urls: ArrayList<ProductModel> = ArrayList()
        urls.add(ProductModel("آویشن","https://media.wired.com/photos/5c9040ee4950d24718d6da99/16:9/w_2400,h_1350,c_limit/shoppingcart-1066110386.jpg"))
        urls.add(ProductModel("دارو گیاهی","https://www.incimages.com/uploaded_files/image/1920x1080/getty_663974538_353364.jpg"))
        urls.add(ProductModel("پماد سردرد","http://www.visitgreece.gr/deployedFiles/StaticFiles/Photos/Generic%20Contents/Forests/mountains_2_560.jpg"))
        urls.add(ProductModel("آویشن","https://media.wired.com/photos/5c9040ee4950d24718d6da99/16:9/w_2400,h_1350,c_limit/shoppingcart-1066110386.jpg"))
        urls.add(ProductModel("دارو گیاهی","https://www.incimages.com/uploaded_files/image/1920x1080/getty_663974538_353364.jpg"))
        urls.add(ProductModel("پماد سردرد","http://www.visitgreece.gr/deployedFiles/StaticFiles/Photos/Generic%20Contents/Forests/mountains_2_560.jpg"))
        urls.add(ProductModel("پماد سردرد","http://www.visitgreece.gr/deployedFiles/StaticFiles/Photos/Generic%20Contents/Forests/mountains_2_560.jpg"))
        return urls
    }
}