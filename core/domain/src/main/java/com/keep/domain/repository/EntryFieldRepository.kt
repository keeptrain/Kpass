package com.keep.domain.repository

import com.keep.model.EntryField
import kotlinx.coroutines.flow.Flow

interface EntryFieldRepository {
    fun getEntryField(): Flow<List<EntryField>>
    fun upsertEntryField(entryField: EntryField)
}