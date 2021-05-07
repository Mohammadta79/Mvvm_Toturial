package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.databinding.ProductFragmentOffersListTemplateBinding
import com.example.shop.model.ProductModel
import com.squareup.picasso.Picasso

class OfferProductAdapter(
    val context: Context,
    val list: ArrayList<ProductModel>,
    val listener: onProductListItemClickListener
) :
    RecyclerView.Adapter<OfferProductAdapter.HomeProductsListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductsListHolder =
        HomeProductsListHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.product_fragment_offers_list_template, parent, false)
        )

    override fun onBindViewHolder(holder: HomeProductsListHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class HomeProductsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ProductFragmentOffersListTemplateBinding.bind(itemView)

        fun bindData(data: ProductModel) {
            var percent:Double = data.offer/100.toDouble()
            var minus = data.price.toInt() * percent
            var offer_price:Int = data.price.toInt() - minus.toInt()
            binding.txtProductPrice.text = offer_price.toString()
            binding.txtProductName.text = data.name
            binding.txtOffer.text = data.offer.toString() + "%"
            Picasso.get().load(data.image).into(binding.imgProduct)
            itemView.setOnClickListener { listener.onProductListItemClick(data) }

        }
    }


}