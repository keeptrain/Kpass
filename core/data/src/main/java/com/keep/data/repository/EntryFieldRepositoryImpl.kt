package com.keep.data.repository

import com.keep.data.model.toEntity
import com.keep.database.BinDispatcher
import com.keep.database.Dispatcher
import com.keep.database.dao.EntryFieldDao
import com.keep.database.model.entry.toExternalModel
import com.keep.domain.repository.EntryFieldRepository
import com.keep.model.EntryField
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class EntryFieldRepositoryImpl @Inject constructor (
    @Dispatcher(BinDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val entryFieldDao: EntryFieldDao
) : EntryFieldRepository {

    override fun getEntryField(): Flow<List<EntryField>> {
        return entryFieldDao.getEntryFields().map { listEntryFields ->
            listEntryFields.map {
                it.toExternalModel()
            }
        }.flowOn(ioDispatcher)
    }

    override fun upsertEntryField(entryField: EntryField) {
        CoroutineScope(ioDispatcher).launch {
            entryFieldDao.upsertEntryFields(entryField.toEntity())
        }
    }
}