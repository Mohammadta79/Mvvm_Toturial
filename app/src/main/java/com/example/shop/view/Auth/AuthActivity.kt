package com.example.shop.view.Auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.shop.databinding.ActivityAuthBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTablayout()
        setupViewPager()
        tabSelected()
    }


    private class LoginPagerAdapter(fm: FragmentManager, var totalTabs: Int) :
        FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> LoginFragment()
                1 -> RegisterFragment()
                else -> null!!
            }
        }

        override fun getCount(): Int {
            return totalTabs
        }
    }

    private fun setupTablayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ورود"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ثبت نام"))
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = LoginPagerAdapter(getSupportFragmentManager(), 2)
        binding.viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(binding.tabLayout))
    }

    private fun tabSelected() {
        binding.tabLayout.setOnTabSelectedListener(
            object : TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager) {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    super.onTabSelected(tab)
                    binding.viewPager.currentItem = tab.position
                }
            })
    }


}