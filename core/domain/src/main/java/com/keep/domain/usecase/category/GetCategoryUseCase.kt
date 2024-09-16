package com.keep.domain.usecase.category

import com.keep.model.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoryUseCase {
    fun getCategory(): Flow<List<Category>>
    suspend fun getCategoryCount() : Int
    fun insertCategory(category: Category)
    fun updateCategory(category: Category)
    fun deleteCategory(category: Category)
}