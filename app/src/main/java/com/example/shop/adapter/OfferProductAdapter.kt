package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.databinding.ProductFragmentListTemplateBinding
import com.example.shop.databinding.ProductFragmentOffersListTemplateBinding
import com.example.shop.model.OfferProductModel
import com.example.shop.model.ProductModel
import com.squareup.picasso.Picasso

class OfferProductAdapter(
    val context: Context,
    val list: ArrayList<OfferProductModel>,
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

        fun bindData(data: OfferProductModel) {
            binding.txtProductPrice.text = data.price
            binding.txtProductName.text = data.name
            binding.txtOffer.text = data.offer
            Picasso.get().load(data.image).into(binding.imgProduct)
            itemView.setOnClickListener { listener.onOffersListItemClick(data) }
        }
    }


}