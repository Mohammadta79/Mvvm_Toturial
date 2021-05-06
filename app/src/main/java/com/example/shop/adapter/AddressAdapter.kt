package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.databinding.AddressFragmentListTemplateBinding
import com.example.shop.model.AddressModel

class AddressAdapter(
    val context: Context,
    val list: ArrayList<AddressModel>
) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    inner class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = AddressFragmentListTemplateBinding.bind(itemView)

        fun bindData(data: AddressModel) {
            binding.txtAddress.text = data.address
            binding.txtCity.text = data.province + " , " + data.city
            binding.txtPhoneNumber.text = data.mobile
            binding.txtPostalCade.text = data.postalCode
            binding.txtReciver.text = data.reciver


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder =
        AddressViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.address_fragment_list_template, parent, false)
        )

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bindData(list[position])

    }

    override fun getItemCount(): Int = list.size;

}