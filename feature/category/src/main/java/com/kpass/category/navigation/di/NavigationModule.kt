package com.kpass.category.navigation.di

import com.keep.common.navigation.NavigationNode
import com.kpass.category.navigation.CategoryNavigationNode
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
    fun bindCategoryNavigation(categoryNavigationNode: CategoryNavigationNode): NavigationNode

}