package com.keep.domain.usecase.entry

import com.keep.model.Entry
import kotlinx.coroutines.flow.Flow

interface GetEntryUseCase {
    fun getEntry() : Flow<List<Entry>>
    fun upsertEntry(entry: Entry)
}