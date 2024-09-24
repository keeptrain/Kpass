package com.keep.category.adapter

import com.keep.model.Category

sealed class CategoryListAdapterItem (val viewType : EnumCategoryListAdapterViewType){
    class CategoryItem(val category: Category) : CategoryListAdapterItem(EnumCategoryListAdapterViewType.CATEGORY)
    class EmptyItem : CategoryListAdapterItem(EnumCategoryListAdapterViewType.EMPTY)
}

enum class EnumCategoryListAdapterViewType {
    CATEGORY,
    EMPTY;

    companion object {
        fun getEnumByOrdinal(index: Int) : EnumCategoryListAdapterViewType {
            return entries[index]
        }
    }
}