package com.example.shop.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shop.R
import com.example.shop.databinding.FragmentDetailsProductBinding


class DetailsProductFragment : Fragment() {

    private lateinit var binding: FragmentDetailsProductBinding
    lateinit var name: String
    lateinit var price: String
    lateinit var desc: String
    lateinit var category: String
    lateinit var weight:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name", null)
            price = it.getString("price", null)
            desc = it.getString("desc", null)
            category = it.getString("category", null)
          //  weight = it.getString("weight", null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

    }
    private fun setData(){
        binding.collapsingToolbar.title = name
        binding.txtPrice.text = price
        //binding.txtProductWeight.text = weight
        binding.txtProductDescription.text = desc
        binding.txtCategory.text = category
    }

}