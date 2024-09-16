package com.keep.domain.repository

import com.keep.model.Entry
import kotlinx.coroutines.flow.Flow

interface EntryRepository {
    fun getEntry() : List<Entry>
    suspend fun getCategoryList() : Flow<List<Entry>>
    suspend fun insertEntry(entry: Entry)

}