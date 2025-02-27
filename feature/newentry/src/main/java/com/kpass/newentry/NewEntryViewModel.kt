package com.kpass.newentry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.domain.usecase.entry.GetEntryUseCase
import com.keep.model.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewEntryViewModel @Inject constructor(
    val entryUseCase: GetEntryUseCase,
    categoryUseCase: GetCategoryUseCase
) : ViewModel() {

    val category = categoryUseCase.getCategory().asLiveData()

    private val _selectedCategory = MutableLiveData<Int?>(null)
    val selectedCategory = _selectedCategory

    /*
        Selected Category from Spinner / BottomSheetDialog
    */
    fun setSelectedCategory(categoryId: Int?) {
        _selectedCategory.value = categoryId
    }

    fun upsertNewEntry(entry: Entry) {
        viewModelScope.launch {
            entryUseCase.upsertEntry(entry)
        }
    }

}