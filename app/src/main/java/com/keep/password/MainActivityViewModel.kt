package com.keep.password

import androidx.lifecycle.ViewModel
import com.keep.domain.category.GetCategoryUseCase
import com.keep.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase
) : ViewModel() {


}