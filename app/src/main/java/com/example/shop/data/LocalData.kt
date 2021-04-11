package com.example.shop.data

import com.example.shop.model.CategoryModel
import com.example.shop.model.ProductModel
import com.example.shop.model.ShopCartModel

object LocalData {

    fun homeSliderItems(): List<String> {
        val urls: MutableList<String> = ArrayList()
        urls.add("https://media.wired.com/photos/5c9040ee4950d24718d6da99/16:9/w_2400,h_1350,c_limit/shoppingcart-1066110386.jpg")
        urls.add("https://www.incimages.com/uploaded_files/image/1920x1080/getty_663974538_353364.jpg")
        urls.add("http://www.visitgreece.gr/deployedFiles/StaticFiles/Photos/Generic%20Contents/Forests/mountains_2_560.jpg")
        return urls
    }
    fun productsItems(): ArrayList<ProductModel> {
        val urls: ArrayList<ProductModel> = ArrayList()
        urls.add(ProductModel("آویشن","https://media.wired.com/photos/5c9040ee4950d24718d6da99/16:9/w_2400,h_1350,c_limit/shoppingcart-1066110386.jpg","29000","نمریسدمریدریبندربمردمبردبردمنبردمنربدربینمر","داروهای گیاهی"))
        urls.add(ProductModel("دارو گیاهی","https://www.incimages.com/uploaded_files/image/1920x1080/getty_663974538_353364.jpg","35000","lkfdnvfkdlnvklfdnvklfnvlkdfnvklnfd","داروهای گیاهی"))
        urls.add(ProductModel("پماد سردرد","http://www.visitgreece.gr/deployedFiles/StaticFiles/Photos/Generic%20Contents/Forests/mountains_2_560.jpg","45000","lkfdnvd'v;nskvnfkvnsdflnvlfknvlkf","داروهای گیاهی"))
        urls.add(ProductModel("آویشن","https://media.wired.com/photos/5c9040ee4950d24718d6da99/16:9/w_2400,h_1350,c_limit/shoppingcart-1066110386.jpg","13000","lksdnvskdfn lfkdn fkdnvdfknvflkdn dflnvfklnvkf","داروهای گیاهی"))
        urls.add(ProductModel("دارو گیاهی","https://www.incimages.com/uploaded_files/image/1920x1080/getty_663974538_353364.jpg","15000","dnvldsfn's;dfnvelrknvpewrinvrepnvrpevnrnv","داروهای گیاهی"))
        urls.add(ProductModel("پماد سردرد","http://www.visitgreece.gr/deployedFiles/StaticFiles/Photos/Generic%20Contents/Forests/mountains_2_560.jpg","17500","klfnvs;dflvmfslkn fslknvsfdklnvflknv","داروهای گیاهی"))
        urls.add(ProductModel("پماد سردرد","http://www.visitgreece.gr/deployedFiles/StaticFiles/Photos/Generic%20Contents/Forests/mountains_2_560.jpg","150000","fkdnvsfkdnvfdlknvfdklnfdlknflkbnfkldbn","داروهای گیاهی"))
        return urls
    }
    fun categoryItems(): List<CategoryModel> {
        val urls: ArrayList<CategoryModel> = ArrayList()
        urls.add(CategoryModel("داروهای گیاهی", productsItems()))
        urls.add(CategoryModel("معجون های طب سنتی", productsItems()))
        urls.add(CategoryModel("ادویه جات", productsItems()))
        return urls
    }
    fun shopcartProduct():ArrayList<ShopCartModel>{
        val list : ArrayList<ShopCartModel> = ArrayList()
        list.add(ShopCartModel("آویشن","https://media.wired.com/photos/5c9040ee4950d24718d6da99/16:9/w_2400,h_1350,c_limit/shoppingcart-1066110386.jpg","29000","معجون های طب سنتی",1,10))
        list.add(ShopCartModel("آویشن","https://media.wired.com/photos/5c9040ee4950d24718d6da99/16:9/w_2400,h_1350,c_limit/shoppingcart-1066110386.jpg","29000","معجون های طب سنتی",2,30))
        return list;
    }
}