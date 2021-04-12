package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.InterFaces.onShopCartItemCLickListener
import com.example.shop.R
import com.example.shop.databinding.CompletePurchaseListTemplateBinding
import com.example.shop.databinding.FragmentCompletePurchaseBinding
import com.example.shop.model.ShopCartModel
import com.squareup.picasso.Picasso

class CompletePurchaseProductsAdapter(
    val context: Context,
    val list: ArrayList<ShopCartModel>,
    val listener: onShopCartItemCLickListener
) : RecyclerView.Adapter<CompletePurchaseProductsAdapter.CompletePurchaseProductsHolder>() {

    inner class CompletePurchaseProductsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CompletePurchaseListTemplateBinding.bind(itemView)

        fun bindData(data: ShopCartModel) {
            Picasso.get().load(data.image).into(binding.imgProduct)
            binding.txtProductName.text = data.name
            itemView.setOnClickListener { listener.onClick(data) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompletePurchaseProductsHolder = CompletePurchaseProductsHolder(
        LayoutInflater.from(context)
            .inflate(R.layout.complete_purchase_list_template, parent, false)
    )

    override fun onBindViewHolder(holder: CompletePurchaseProductsHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}