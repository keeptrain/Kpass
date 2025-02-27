package com.kpass.newentry.navigation.di

import com.keep.common.navigation.NavigationNode
import com.kpass.newentry.navigation.NewEntryNavigationNode
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @IntoSet
    @Binds
    fun bindsEntryNavigation(newEntryNavigationNode: NewEntryNavigationNode) : NavigationNode
}