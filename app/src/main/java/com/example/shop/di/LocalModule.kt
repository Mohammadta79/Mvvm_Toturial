package com.example.shop.di

import android.content.Context
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import com.example.shop.data.LocalData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object LocalModule {
    @Provides
    @Named("ProductFragment")
    fun homeSliderProvider(@ApplicationContext context: Context): BaseBannerAdapter =
        BaseBannerAdapter(context, LocalData.homeSliderItems())

    @Provides
    @Named("UserFragment")
    fun userSliderProvider(@ApplicationContext context: Context): BaseBannerAdapter =
        BaseBannerAdapter(context, LocalData.userSliderItems())
}