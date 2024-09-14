package com.keep.data.model

import com.keep.database.model.CategoryEntity
import com.keep.model.Category

fun Category.toEntity() = CategoryEntity(
    id = id,
    name = name
)