package com.keep.domain.usecase.category

import com.keep.domain.repository.CategoryRepository
import com.keep.model.Category
import kotlinx.coroutines.flow.Flow
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

    override fun insertCategory(category: Category) {
        repository.insertCategory(category)
    }

    override fun updateCategory(category: Category) {
         repository.updateCategory(category)
    }

    override suspend fun getLastPosition(): Int {
        return repository.getLastPosition()
    }

    override fun updateCategoryPosition(category: List<Category>) {
        repository.updateCategoryPosition(category)
    }

    override fun deleteCategory(category: Category) {
         repository.deleteCategory(category)
    }
}