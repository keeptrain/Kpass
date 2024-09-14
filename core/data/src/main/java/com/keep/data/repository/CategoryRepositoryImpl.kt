package com.keep.data.repository

import com.keep.data.model.toEntity
import com.keep.database.dao.CategoryDao
import com.keep.database.model.toExternalModel
import com.keep.domain.repository.CategoryRepository
import com.keep.model.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor (
    //private val ioDispatcher: CoroutineDispatcher,
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override fun getCategory(): List<Category> {
       return categoryDao.getCategoryEntity().map {
           it.toExternalModel()
       }
    }

    override suspend fun getCountCategory(): Int {
        return withContext(Dispatchers.IO) {
            categoryDao.getCategoryCount()
        }
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
        categoryDao.deleteCategory(category.toEntity())
    }
}