package com.example.shop.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.adapter.AddressAdapter
import com.example.shop.data.LocalData
import com.example.shop.viewModel.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object LocalModule {

    @Provides
    @FragmentScoped
    fun provideHomeSliderAdapter(@ActivityContext context: Context): BaseBannerAdapter =
        BaseBannerAdapter(context, LocalData.homeSliderItems())



}