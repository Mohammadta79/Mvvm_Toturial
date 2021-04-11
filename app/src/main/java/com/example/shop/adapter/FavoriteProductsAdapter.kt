package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.InterFaces.onProductListItemClickListener
import com.example.shop.R
import com.example.shop.databinding.FavoriteFragmentListTemplateBinding
import com.example.shop.model.ProductModel
import com.squareup.picasso.Picasso

class FavoriteProductsAdapter(
    val context: Context,
    val list:ArrayList<ProductModel>,
    val listener:onProductListItemClickListener
):
    RecyclerView.Adapter<FavoriteProductsAdapter.FavoriteProductsHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteProductsHolder =
        FavoriteProductsHolder(LayoutInflater.from(context).inflate(R.layout.favorite_fragment_list_template,parent,false))


    override fun onBindViewHolder(holder: FavoriteProductsHolder, position: Int) {
       holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
     fun deleteItem(postition:Int){
        list.removeAt(postition)
         notifyItemRemoved(postition)
    }





    inner class FavoriteProductsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FavoriteFragmentListTemplateBinding.bind(itemView)

        fun bindData(data:ProductModel){
           Picasso.get().load(data.image).into(binding.imgProductFavorite)
            binding.txtProductNameFavorite.text = data.name
            binding.txtProductPriceFavorite.text = data.price

            itemView.setOnClickListener{listener.onProductListItemClick(data)}
            binding.imgDeleteFavorite.setOnClickListener { deleteItem(adapterPosition) }
        }

    }
}