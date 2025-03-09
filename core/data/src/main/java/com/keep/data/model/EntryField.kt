package com.keep.data.model

import com.keep.database.model.entry.EntryFieldEntity
import com.keep.model.EntryField

fun EntryField.toEntity() = EntryFieldEntity(
    entryId = entryId,
    fieldId = fieldId,
    fieldValue = fieldValue
)