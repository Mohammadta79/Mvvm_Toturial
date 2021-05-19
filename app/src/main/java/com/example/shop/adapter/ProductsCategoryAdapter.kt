package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.databinding.FavoriteFragmentListTemplateBinding
import com.example.shop.databinding.ProductCategoryListTemplateBinding
import com.example.shop.model.ProductModel
import com.squareup.picasso.Picasso

class ProductsCategoryAdapter(
    val context: Context,
    val list: ArrayList<ProductModel>,
    val listener: onProductListItemClickListener
) :
    RecyclerView.Adapter<ProductsCategoryAdapter.ProductsCategoryHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsCategoryHolder =
        ProductsCategoryHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.product_category_list_template, parent, false)
        )


    override fun onBindViewHolder(holder: ProductsCategoryHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size




    inner class ProductsCategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ProductCategoryListTemplateBinding.bind(itemView)

        fun bindData(data: ProductModel) {
            if (data.offer == 0) {
                binding.txtOffer.visibility = View.GONE
                binding.txtProductPrice.text = data.price
            } else {
                binding.txtOffer.visibility = View.VISIBLE
                var percent: Double = data.offer / 100.toDouble()
                var minus = data.price.toInt() * percent
                var offer_price: Int = data.price.toInt() - minus.toInt()
                binding.txtOffer.text = data.offer.toString() + "%"
                binding.txtProductPrice.text = offer_price.toString()
            }
            Picasso.get().load(data.image).into(binding.imgProduct)
            binding.txtProductName.text = data.name


            itemView.setOnClickListener { listener.onProductListItemClick(data) }

        }

    }
}