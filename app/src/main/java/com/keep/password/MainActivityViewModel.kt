package com.keep.password

import androidx.lifecycle.ViewModel
import com.keep.domain.usecase.category.GetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
     useCase: GetCategoryUseCase,
) : ViewModel() {


}