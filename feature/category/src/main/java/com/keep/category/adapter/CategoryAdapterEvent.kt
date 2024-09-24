package com.keep.category.adapter

import com.keep.model.Category

interface CategoryAdapterEvent {

    fun addCategory()

    fun editCategory(category: Category)

    fun onMoreClick(category: Category)

}