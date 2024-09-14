package com.keep.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryActivityViewModel @Inject constructor(
    val useCase: GetCategoryUseCase
): ViewModel() {

    private val _categoryCount = MutableLiveData<Int>()
    val categoryCount: LiveData<Int> get() = _categoryCount

    fun getCategory(category: Category) {
        useCase.getCategory()
    }

   fun insertCategory(categoryName: String) {
        viewModelScope.launch {
            val categoryCount = useCase.getCategoryCount()
            val newCategoryId = "category-${categoryCount + 1}" // Generate ID baru
            val newCategory = Category(id = newCategoryId, name = categoryName)
            useCase.insertCategory(newCategory)
        }

    }

    fun updateCategory(category: Category) {
        useCase.updateCategory(category)
    }

    fun deleteCategory(category: Category) {
        useCase.deleteCategory(category)
    }
}