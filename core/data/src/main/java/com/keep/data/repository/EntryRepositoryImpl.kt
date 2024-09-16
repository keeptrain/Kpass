package com.keep.data.repository

import com.keep.data.model.toEntity
import com.keep.database.dao.EntryDao
import com.keep.database.model.toExternalModel
import com.keep.domain.repository.EntryRepository
import com.keep.model.Entry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EntryRepositoryImpl @Inject constructor(
    private val entryDao: EntryDao
) : EntryRepository {
    override fun getEntry(): List<Entry> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryList(): Flow<List<Entry>> {
        return entryDao.getCategoryList().map {
            it.map {
                it.toExternalModel()
            }
        }


    }

    override suspend fun insertEntry(entry: Entry) {
        return entryDao.insertEntry(entry.toEntity())
    }
}