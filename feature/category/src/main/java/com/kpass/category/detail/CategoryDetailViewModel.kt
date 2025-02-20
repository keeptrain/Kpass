package com.kpass.category.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase
) : ViewModel() {

    private val _category = MutableLiveData<Category>()
    val category: LiveData<Category> = _category

    fun updateCategory(category: Category) {
        useCase.updateCategory(category)
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            useCase.deleteCategory(category)
        }
    }

    // TODO: Implement the ViewModel
}