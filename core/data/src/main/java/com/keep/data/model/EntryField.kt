package com.keep.data.model

import com.keep.database.model.entry.EntryFieldsEntity
import com.keep.model.EntryFields

fun EntryFields.toEntity() = EntryFieldsEntity(
    entryId = entryId,
    fieldName = fieldName,
    fieldValue = fieldValue
)