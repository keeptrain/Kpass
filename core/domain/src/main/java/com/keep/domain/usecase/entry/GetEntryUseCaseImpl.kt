package com.keep.domain.usecase.entry

import com.keep.domain.repository.EntryRepository
import com.keep.model.Entry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEntryUseCaseImpl @Inject constructor(
    private val repository: EntryRepository
) : GetEntryUseCase {

    override fun getEntry(): Flow<List<Entry>> {
        return repository.getEntry()
    }

    override fun upsertEntry(entry: Entry) {
        repository.upsertEntry(entry)
    }

}