package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.databinding.HomeFragmentListTemplateBinding
import com.example.shop.model.CategoryModel
import javax.inject.Inject

class HomeProductsAdapter(
    val context: Context,
    var list: List<CategoryModel>,
    var homeProductsListItemAdapter: HomeProductsListItemAdapter

) : RecyclerView.Adapter<HomeProductsAdapter.HomeProductsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductsHolder =
        HomeProductsHolder(LayoutInflater.from(context).inflate(R.layout.home_fragment_list_template,parent,false))


    override fun onBindViewHolder(holder: HomeProductsHolder, position: Int) {
       holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
       return list.size
    }


    inner class HomeProductsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = HomeFragmentListTemplateBinding.bind(itemView)

        fun bindData(data: CategoryModel) {
            binding.txtCategory.text = data.category_name
           binding.listRecyclerView.apply {
               layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
               adapter = homeProductsListItemAdapter
           }
        }
    }

}