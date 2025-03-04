package com.keep.domain.repository

import com.keep.model.EntryFields
import kotlinx.coroutines.flow.Flow

interface EntryFieldRepository {
    fun getEntryField(): Flow<List<EntryFields>>
    fun upsertEntryField(entryFields: EntryFields)
}