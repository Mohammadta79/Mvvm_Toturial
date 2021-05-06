package com.example.shop.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController

import androidx.navigation.ui.setupWithNavController
import com.example.shop.R
import com.example.shop.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/*developed
 by
 Mohammadta79*/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup bottom navigation
        binding.bottomNavigationView.setupWithNavController(findNavController(R.id.fragment))

    }


}