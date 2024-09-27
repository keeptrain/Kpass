package com.keep.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.keep.category.adapter.CategoryListAdapterItem
import com.keep.common.util.Event
import com.keep.domain.ValidationResult
import com.keep.domain.ui.category.CategoryValidationUseCase
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryActivityViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase,
    private val validationUseCase: CategoryValidationUseCase,
): ViewModel() {

    val categories: LiveData<List<Category>> = useCase.getCategory().asLiveData()

    private val _validationResult = MutableLiveData<Event<ValidationResult>>()
    val validationResult : LiveData<Event<ValidationResult>> = _validationResult

    private val _insertResult = MutableLiveData<Event<Boolean>>()
    val insertResult : LiveData<Event<Boolean>> = _insertResult

    fun isCategoryNameExists(categoryName : String, callback: (Boolean) -> Unit) {
       viewModelScope.launch {
           useCase.getCategory().collect { categoryList ->
               val exists = categoryList.any {
                   it.name.equals(categoryName, ignoreCase = true)
               }
               callback(exists)
           }
       }
    }

    fun insertCategoryWithFieldsValidation(category: Category) {
        val result = validationUseCase.validateTitle(category.name)
        if (result.successful) {
            useCase.insertCategory(category)
            _insertResult.value = Event(true)
        } else {
            _validationResult.value = Event(result)
        }
    }

    fun insertCategory(category: Category) {
        useCase.insertCategory(category)
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