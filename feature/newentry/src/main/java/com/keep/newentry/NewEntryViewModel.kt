package com.keep.newentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.keep.domain.usecase.category.GetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewEntryViewModel @Inject constructor(
    //private val entryUseCase: GetEntryUseCase,
    categoryUseCase: GetCategoryUseCase
) : ViewModel() {

    val category = categoryUseCase.getCategory().asLiveData()

    fun insertNewEntry() {

    }

    /*fun getCategoryName() {
        viewModelScope.launch {
            entryUseCase.getCategoryList().asLiveData()
        }
    }*/
    //val categoryName = entryUseCase.getCategoryList()
}