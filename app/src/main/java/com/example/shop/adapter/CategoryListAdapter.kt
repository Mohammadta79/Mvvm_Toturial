package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.InterFaces.onCategoryListClickListener
import com.example.shop.R

import com.example.shop.databinding.ProductFragmentCategoryListTemplateBinding
import com.example.shop.model.CategoryModel
import com.squareup.picasso.Picasso

class CategoryListAdapter(
    val context: Context,
    var list: List<CategoryModel>,
    var listener: onCategoryListClickListener

) : RecyclerView.Adapter<CategoryListAdapter.HomeCategoryHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryHolder =
        HomeCategoryHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.product_fragment_category_list_template, parent, false)
        )


    override fun onBindViewHolder(holder: HomeCategoryHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size


    inner class HomeCategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ProductFragmentCategoryListTemplateBinding.bind(itemView)

        fun bindData(data: CategoryModel) {
            binding.txtCategoryName.text = data.name
            Picasso.get().load(data.image).into(binding.imgCategory)
            itemView.setOnClickListener{listener.onCategoryClick(data)}
        }


    }


}