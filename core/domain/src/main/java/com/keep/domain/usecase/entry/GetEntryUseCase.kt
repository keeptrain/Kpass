package com.keep.domain.usecase.entry

import com.keep.model.Entry
import kotlinx.coroutines.flow.Flow

interface GetEntryUseCase {
    fun getEntry() : List<Entry>
    //suspend fun getCategoryList() : Flow<List<Entry>>
    suspend fun insertEntry(entry: Entry)

}