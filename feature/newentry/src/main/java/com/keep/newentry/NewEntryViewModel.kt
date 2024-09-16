package com.keep.newentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.domain.usecase.entry.GetEntryUseCase
import com.keep.model.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewEntryViewModel @Inject constructor(
    private val categoryUseCase: GetCategoryUseCase
) : ViewModel() {

    fun getCategoryList() {
        viewModelScope.launch {
            categoryUseCase.getCategory()
        }
    }
}