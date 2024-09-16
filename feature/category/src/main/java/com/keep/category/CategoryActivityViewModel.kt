package com.keep.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryActivityViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase
): ViewModel() {

    // LiveData untuk kategori
    private val _categories = MutableLiveData<Flow<List<Category>>>()
    val categories: LiveData<Flow<List<Category>>> get() = _categories

    fun getCategory() {
        viewModelScope.launch {
            val categoryList = useCase.getCategory()
            _categories.postValue(categoryList)
        }
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