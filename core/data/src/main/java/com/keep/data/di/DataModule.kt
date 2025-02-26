package com.keep.data.di

import com.keep.data.repository.CategoryRepositoryImpl
import com.keep.data.repository.EntryRepositoryImpl
import com.keep.domain.repository.CategoryRepository
import com.keep.domain.repository.EntryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsEntryRepository(
        entryRepositoryImpl: EntryRepositoryImpl
    ): EntryRepository

    @Binds
    internal abstract fun bindsCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository
}