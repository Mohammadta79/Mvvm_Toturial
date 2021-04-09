package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.databinding.HomeFragmentListItemTemplateBinding
import com.example.shop.model.ProductModel
import com.squareup.picasso.Picasso

class HomeProductsListItemAdapter(
    val context: Context,
    val list: List<ProductModel>
):
    RecyclerView.Adapter<HomeProductsListItemAdapter.HomeProductsListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductsListHolder =
        HomeProductsListHolder(LayoutInflater.from(context).inflate(R.layout.home_fragment_list_item_template,parent,false))

    override fun onBindViewHolder(holder: HomeProductsListHolder, position: Int) {
       holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class HomeProductsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = HomeFragmentListItemTemplateBinding.bind(itemView)

        fun bindData(data: ProductModel){
            binding.txtProductName.text = data.name
            Picasso.get().load(data.image).into(binding.imgProduct)
        }


    }
}