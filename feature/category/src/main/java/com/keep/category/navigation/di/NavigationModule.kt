package com.keep.category.navigation.di

import com.keep.category.navigation.CategoryNavigationNode
import com.keep.common.navigation.NavigationNode
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