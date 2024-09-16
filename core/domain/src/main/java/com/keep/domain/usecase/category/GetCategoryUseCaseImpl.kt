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

    override suspend fun getCategoryCount(): Int {
        return repository.getCountCategory()
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