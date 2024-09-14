package com.keep.database.di

import com.keep.database.KpsDatabase
import com.keep.database.dao.CategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesCategoryDao(
        database: KpsDatabase
    ): CategoryDao = database.categoryDao()
}