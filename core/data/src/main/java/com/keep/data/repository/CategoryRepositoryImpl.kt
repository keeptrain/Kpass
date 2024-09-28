package com.keep.data.repository

import com.keep.data.model.toEntity
import com.keep.database.dao.CategoryDao
import com.keep.database.model.CategoryEntity
import com.keep.database.model.toExternalModel
import com.keep.domain.repository.CategoryRepository
import com.keep.model.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor (
    //private val ioDispatcher: CoroutineDispatcher,
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override fun getCategory(): Flow<List<Category>> {
       return categoryDao.getCategoryEntity().map { it ->
           it.map {
               it.toExternalModel()
           }
       }.flowOn(Dispatchers.IO)
    }

    override fun insertCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao.insertCategory(category.toEntity())
        }
    }

    override fun updateCategory(category: Category) {
       //categoryDao.insertCategory(category.toEntity())
    }

    override fun deleteCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            categoryDao.deleteCategory(category.toEntity())
        }
    }
}