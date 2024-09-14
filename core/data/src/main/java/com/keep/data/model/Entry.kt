package com.keep.data.model

import com.keep.database.model.KpassEntity
import com.keep.model.Entry

fun Entry.toEntity() = KpassEntity(
    id,
    title,
    categoryId,
    username,
    password,
    website
)
