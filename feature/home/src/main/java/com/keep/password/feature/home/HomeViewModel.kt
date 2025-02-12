package com.keep.password.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.keep.domain.usecase.category.GetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCase: GetCategoryUseCase
) : ViewModel() {

    val categories = useCase.getCategory().asLiveData()

}