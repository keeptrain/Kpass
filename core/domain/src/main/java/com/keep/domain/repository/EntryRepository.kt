package com.keep.domain.repository

import com.keep.model.Entry
import kotlinx.coroutines.flow.Flow

interface EntryRepository {
    fun getEntry() : Flow<List<Entry>>
    fun upsertEntry(entry: Entry)
}