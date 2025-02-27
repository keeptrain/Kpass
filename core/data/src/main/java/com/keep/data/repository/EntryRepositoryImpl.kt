package com.keep.data.repository

import com.keep.data.model.toEntity
import com.keep.database.BinDispatcher
import com.keep.database.Dispatcher
import com.keep.database.dao.EntryDao
import com.keep.database.model.entry.toExternalModel
import com.keep.domain.repository.EntryRepository
import com.keep.model.Entry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class EntryRepositoryImpl @Inject constructor(
    @Dispatcher(BinDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val entryDao: EntryDao
) : EntryRepository {

    override fun getEntry(): Flow<List<Entry>> {
        return entryDao.getEntryEntity().map {
            it.map {
                it.toExternalModel()
            }
        }.flowOn(ioDispatcher)
    }

    override fun upsertEntry(entry: Entry) {
        CoroutineScope(ioDispatcher).launch {
            entryDao.upsertEntry(entry.toEntity())
        }
    }
}