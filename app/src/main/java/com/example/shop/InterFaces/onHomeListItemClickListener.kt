package com.example.shop.InterFaces

import com.example.shop.model.ProductModel
import javax.inject.Inject


interface onHomeListItemClickListener {
    fun onHomeListItemClick(productModel: ProductModel)
}