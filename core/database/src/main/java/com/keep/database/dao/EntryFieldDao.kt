package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.keep.database.model.entry.EntryFieldEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryFieldDao {

    @Query("SELECT * FROM entry_field")
    fun getEntryFields(): Flow<List<EntryFieldEntity>>

    @Upsert(entity = EntryFieldEntity::class)
    suspend fun upsertEntryFields(entryFields: EntryFieldEntity)
}