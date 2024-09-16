package com.keep.data.model

import com.keep.database.model.EntryEntity
import com.keep.model.Entry

fun Entry.toEntity() = EntryEntity(
    id,
    title,
    categoryId,
    username,
    password,
    website
)
