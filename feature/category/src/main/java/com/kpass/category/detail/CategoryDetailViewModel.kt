package com.kpass.category.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase
) : ViewModel() {

    private val _category = MutableLiveData<Category>()
    val category: LiveData<Category> = _category

    private val _categoryUpdate = MutableStateFlow<Category?>(null)
    val categoryUpdate = _categoryUpdate.asStateFlow()

    val categories = useCase.getCategory().asLiveData()

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            useCase.deleteCategory(category)
        }
    }

}