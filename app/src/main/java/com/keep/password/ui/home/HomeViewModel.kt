package com.keep.password.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.keep.category.adapter.CategoryListAdapterItem
import com.keep.domain.usecase.category.GetCategoryUseCase
import com.keep.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetCategoryUseCase
): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val categories: LiveData<List<Category>> = useCase.getCategory().asLiveData()
//
//    fun generateCategoryAdapterList(list: List<Category>): List<CategoryListAdapterItem> {
//        val array: MutableList<CategoryListAdapterItem> = mutableListOf()
//        if (list.isEmpty()) {
//            listOf(CategoryListAdapterItem.EmptyItem())
//        } else {
//            list.forEach {
//                array.add(CategoryListAdapterItem.CategoryItem(it))
//            }
//        }
//        return array
//    }


    val text: LiveData<String> = _text
}