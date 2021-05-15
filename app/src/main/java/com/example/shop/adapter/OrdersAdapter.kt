package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.databinding.OderListTemplateBinding
import com.example.shop.model.OrdersModel
import com.squareup.picasso.Picasso

class OrdersAdapter(
    val context: Context,
    val list: ArrayList<OrdersModel>
) : RecyclerView.Adapter<OrdersAdapter.OrderListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListHolder =
        OrderListHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.oder_list_template, parent, false)
        )

    override fun onBindViewHolder(holder: OrderListHolder, position: Int) {

        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size


    inner class OrderListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = OderListTemplateBinding.bind(itemView)
        fun bindData(data: OrdersModel) {
            binding.txtPaycode.text = data.paycode
            binding.txtAddress.text = data.province + " , "+data.city
            binding.txtPrice.text = data.price
            binding.txtProductName.text = data.name
            Picasso.get().load(data.image).into(binding.imgProduct)
        }
    }
}