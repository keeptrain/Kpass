package com.keep.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.keep.category.adapter.CategoryListAdapterItem
import com.keep.domain.ui.category.CategoryValidationUseCase
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryActivityViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase,
    private val validationUseCase: CategoryValidationUseCase,
): ViewModel() {

    val categories: LiveData<List<Category>> = useCase.getCategory().asLiveData()

    private val _insertResult = MutableLiveData<Boolean>()
    val insertResult : LiveData<Boolean> = _insertResult


    fun insertCategoryWithFieldsValidation(category: Category) {
        val result = validationUseCase.validateTitle(category.name)
        if (result.successful) {
            useCase.insertCategory(category)
            _insertResult.value = true

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

    fun generateCategoryAdapterList(list:List<Category>): List<CategoryListAdapterItem> {
        val array: MutableList<CategoryListAdapterItem> = mutableListOf()
        if (list.isEmpty()) {
            //array.add(CategoryListAdapterItem.)
        } else {
            list.forEach { array.add(CategoryListAdapterItem.CategoryItem(it)) }
        }
        return array
    }
}