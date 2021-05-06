package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.databinding.ProductFragmentListTemplateBinding
import com.example.shop.model.ProductModel
import com.squareup.picasso.Picasso

class BestSellerProductAdapter(
    val context: Context,
    val list: ArrayList<ProductModel>,
    val listener: onProductListItemClickListener
) :
    RecyclerView.Adapter<BestSellerProductAdapter.HomeProductsListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductsListHolder =
        HomeProductsListHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.product_fragment_list_template, parent, false)
        )

    override fun onBindViewHolder(holder: HomeProductsListHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class HomeProductsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ProductFragmentListTemplateBinding.bind(itemView)

        fun bindData(data: ProductModel) {
            binding.txtProductPrice.text = data.price
            binding.txtProductName.text = data.name
            Picasso.get().load(data.image).into(binding.imgProduct)
            itemView.setOnClickListener { listener.onProductListItemClick(data) }
        }
    }


}