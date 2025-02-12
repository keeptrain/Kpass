package com.keep.password.feature.home.navigation.di

import com.keep.common.navigation.NavigationNode
import com.keep.password.feature.home.navigation.HomeNavigationNode
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
    fun bindHomeNavigation(homeNavigationNode: HomeNavigationNode): NavigationNode

}