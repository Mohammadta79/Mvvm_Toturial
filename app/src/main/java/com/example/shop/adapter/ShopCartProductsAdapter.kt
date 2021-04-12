package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.InterFaces.onShopCartItemCLickListener
import com.example.shop.R
import com.example.shop.databinding.ShopCartFragmentListTemplateBinding
import com.example.shop.model.ShopCartModel
import com.squareup.picasso.Picasso


class ShopCartProductsAdapter(
    val context: Context,
    val list: ArrayList<ShopCartModel>,
    val lisener: onShopCartItemCLickListener
) :
    RecyclerView.Adapter<ShopCartProductsAdapter.ShopCartProductsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopCartProductsHolder =
        ShopCartProductsHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.shop_cart_fragment_list_template, parent, false)
        )

    override fun onBindViewHolder(holder: ShopCartProductsHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun deleteItem(id:Int){
        list.forEachIndexed{index,_ ->
            if (list[index].id == id.toString()){
                list.removeAt(index)
                notifyItemRemoved(index)
            }
        }
    }


    inner class ShopCartProductsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ShopCartFragmentListTemplateBinding.bind(itemView)

        fun bindData(data: ShopCartModel) {
            Picasso.get().load(data.image).into(binding.imgProductCart)
            binding.txtCartPrice.text = data.price
            binding.txtProductNameCart.text = data.name
            binding.txtNumOfCart.text = data.count.toString()
            binding.txtCategoryCart.text = data.category
            if (data.count == 1) {
                binding.imgMinesCart.setImageResource(R.drawable.ic_delete)
            } else {
                binding.imgMinesCart.setImageResource(R.drawable.ic_mines)
            }
            itemView.setOnClickListener { lisener.onClick(data) }

            binding.imgAddCart.setOnClickListener {
                lisener.onChangeCount("add", data.id)
            }
            binding.imgMinesCart.setOnClickListener {
                if (binding.imgMinesCart.drawable == AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_delete
                    )

                ) {
                    lisener.onChangeCount("delete", data.id)
                } else {
                    lisener.onChangeCount("mines", data.id)
                }
            }

        }
    }
}