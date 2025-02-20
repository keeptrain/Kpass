package com.kpass.category.adapter

import com.keep.model.Category

interface CategoryAdapterEvent {

    fun toDetailScreen(category: Category)

    fun reorderCategory() : Boolean

}

object NoCategoryAdapterEvent : CategoryAdapterEvent {

    override fun toDetailScreen(category: Category) {
        false
    }

    override fun reorderCategory(): Boolean {
        return true
    }

}