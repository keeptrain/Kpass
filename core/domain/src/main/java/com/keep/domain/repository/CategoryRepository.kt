package com.keep.domain.repository

import com.keep.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategory(): List<Category>
    suspend fun getCountCategory(): Int
    fun insertCategory(category: Category)
    fun updateCategory(category: Category)
    fun deleteCategory(category: Category)
}