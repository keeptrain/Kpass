package com.keep.domain.usecase.entry

import com.keep.model.Entry

interface GetEntryUseCase {
    fun getEntry() : List<Entry>
    suspend fun getCategoryList() : List<Entry>
    suspend fun insertEntry(entry: Entry)

}