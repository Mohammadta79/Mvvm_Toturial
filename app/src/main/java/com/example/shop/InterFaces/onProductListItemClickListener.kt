package com.example.shop.InterFaces

import com.example.shop.model.OfferProductModel
import com.example.shop.model.ProductModel


interface onProductListItemClickListener {
    fun onProductListItemClick(productModel: ProductModel)
    fun onOffersListItemClick(offerProductModel: OfferProductModel)
}