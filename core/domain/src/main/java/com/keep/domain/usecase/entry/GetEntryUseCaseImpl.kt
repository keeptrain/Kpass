package com.keep.domain.usecase.entry

import com.keep.domain.repository.EntryRepository
import com.keep.model.Entry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEntryUseCaseImpl @Inject constructor(
    private val repository: EntryRepository
) : GetEntryUseCase {
    override fun getEntry(): List<Entry> {
        TODO("Not yet implemented")
    }

    /*override suspend fun getCategoryList(): Flow<List<Entry>> {
        return repository.getCategoryList()
    }*/

    override suspend fun insertEntry(entry: Entry) {
        repository.insertEntry(entry)
    }
}