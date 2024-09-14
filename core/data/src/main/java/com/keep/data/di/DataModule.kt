package com.keep.data.di

import com.keep.data.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    //@Binds
    /*internal abstract fun bindsCategoryRepository(
        categoryRepository
    ): CategoryRepository*/
}