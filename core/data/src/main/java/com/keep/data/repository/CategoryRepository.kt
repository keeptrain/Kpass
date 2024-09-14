package com.keep.data.repository

import com.keep.database.dao.CategoryDao
import com.keep.model.Category
import kotlinx.coroutines.flow.Flow

internal class CategoryRepository  (
    private val categoryDao: CategoryDao,
) {

    //fun getCategory() : Flow<List<Category>>
}