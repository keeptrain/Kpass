package com.keep.domain.category

import com.keep.model.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoryUseCase {
    fun getCategory(): List<Category>
    suspend fun getCategoryCount() : Int
    fun insertCategory(category: Category)
    fun updateCategory(category: Category)
    fun deleteCategory(category: Category)
}