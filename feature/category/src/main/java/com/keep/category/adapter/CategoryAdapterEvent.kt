package com.keep.category.adapter

import com.keep.model.Category

interface CategoryAdapterEvent {

    fun addCategory()

    fun updateCategory(category: Category)

    fun deleteCategory(category: Category)

    fun onMoreClick(category: Category)

}