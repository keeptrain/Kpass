package com.keep.database.di

import com.keep.database.KpsDatabase
import com.keep.database.dao.CategoryDao
import com.keep.database.dao.EntryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesEntryDao(
        database: KpsDatabase
    ): EntryDao = database.entryDao()

    @Provides
    fun providesCategoryDao(
        database: KpsDatabase
    ): CategoryDao = database.categoryDao()
}