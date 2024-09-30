package com.keep.domain.usecase.category

import com.keep.domain.repository.CategoryRepository
import com.keep.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCategoryUseCaseImpl @Inject constructor (
    private val repository: CategoryRepository
) : GetCategoryUseCase {

    override fun getCategory(): Flow<List<Category>> {
        return repository.getCategory().map {
            it
        }
    }


    suspend fun isCategoryNameExists(categoryName: String): Boolean { // Pastikan tipe datanya benar
        return repository.categoryExists().firstOrNull { category ->
            category.name.equals(categoryName, ignoreCase = true)
        } != null
    }


    override fun insertCategory(category: Category) {
        return repository.insertCategory(category)
    }

    override fun updateCategory(category: Category) {
        return repository.updateCategory(category)
    }

    override fun deleteCategory(category: Category) {
        return repository.deleteCategory(category)
    }
}