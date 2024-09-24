package com.keep.domain.di

import com.keep.domain.ui.category.CategoryValidationUseCase
import com.keep.domain.ui.category.CategoryValidationUseCaseImpl
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.domain.usecase.category.GetCategoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetCategoryUseCase(
        categoryUseCaseImpl: GetCategoryUseCaseImpl
    ) : GetCategoryUseCase

    @Binds
    abstract fun bindValidationCategoryUseCase(
        categoryValidationUseCaseImpl: CategoryValidationUseCaseImpl
    ) : CategoryValidationUseCase
}