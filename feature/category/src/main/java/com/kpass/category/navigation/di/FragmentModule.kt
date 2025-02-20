package com.kpass.category.navigation.di

import com.kpass.category.navigation.CategoryCommunicatorImpl
import com.kpass.category.shared.CategoryFeatureCommunicator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface FragmentModule {
    @Binds
    fun getCommunicator(categoryCommunicatorImpl: CategoryCommunicatorImpl) : CategoryFeatureCommunicator
}