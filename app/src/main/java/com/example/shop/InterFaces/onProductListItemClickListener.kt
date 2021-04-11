package com.example.shop.InterFaces

import com.example.shop.model.ProductModel
import javax.inject.Inject


interface onProductListItemClickListener {
    fun onProductListItemClick(productModel: ProductModel)
}