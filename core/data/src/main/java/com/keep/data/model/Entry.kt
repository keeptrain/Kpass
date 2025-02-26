package com.keep.data.model

import com.keep.database.model.entry.EntryEntity
import com.keep.model.Entry

fun Entry.toEntity() = EntryEntity(
    id,
    title,
    categoryId,
)
