package com.kpass.newentry.navigation.di

import com.kpass.newentry.navigation.NewEntryCommunicatorImpl
import com.kpass.newentry.shared.NewEntryFeatureCommunicator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface FragmentModule {

    @Binds
    fun getFeature(newEntryCommunicatorImpl: NewEntryCommunicatorImpl): NewEntryFeatureCommunicator
}