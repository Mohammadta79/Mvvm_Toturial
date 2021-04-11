package com.example.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    val lisener : onShopCartItemCLickListener
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

    fun minesItemCount(txtCount: TextView, position: Int) {
        if (txtCount.text.toString().toInt() != 1) {
            var count = txtCount.text.toString().toInt()
            count--
            txtCount.text = count.toString()
            //TODO:set count & reminder of products to DB
        } else {
            list.removeAt(position)
            notifyItemRemoved(position)
            //TODO:remove Item in DB
        }
    }

    fun addItemCount(txtCount: TextView) {
        var count = txtCount.text.toString().toInt()
        count++
        txtCount.text = count.toString()
        //TODO:set count & reminder of products to DB
    }


    inner class ShopCartProductsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ShopCartFragmentListTemplateBinding.bind(itemView)

        fun bindData(data: ShopCartModel) {
            Picasso.get().load(data.image).into(binding.imgProductCart)
            binding.txtCartPrice.text = data.price
            binding.txtProductNameCart.text = data.name
            binding.txtNumOfCart.text = data.count.toString()
            binding.txtCategoryCart.text = data.category

            itemView.setOnClickListener { lisener.onClick(data) }

            if (data.count == 1) {
                binding.imgMinesCart.setImageResource(R.drawable.ic_delete)
            } else {
                binding.imgMinesCart.setImageResource(R.drawable.ic_mines)
            }
            binding.imgMinesCart.setOnClickListener {
                minesItemCount(
                    binding.txtNumOfCart,
                    adapterPosition
                )
            }

            binding.imgAddCart.setOnClickListener {
                if (data.reminder != 0) {
                addItemCount(binding.txtNumOfCart)
                }else{
                    binding.imgAddCart.isEnabled = false
                }
            }

        }
    }
}