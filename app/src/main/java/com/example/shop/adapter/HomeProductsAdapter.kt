package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.databinding.HomeFragmentListTemplateBinding
import com.example.shop.model.ProductModel
import com.squareup.picasso.Picasso

class HomeProductsAdapter(
    val context: Context,
    var list: List<ProductModel>

) : RecyclerView.Adapter<HomeProductsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_fragment_list_template,parent,false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
       return list.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = HomeFragmentListTemplateBinding.bind(itemView)

        fun bindData(data: ProductModel) {
            binding.txtProductName.text = data.name
           Picasso.get().load(data.image).into(binding.imgProduct)
        }
    }

}