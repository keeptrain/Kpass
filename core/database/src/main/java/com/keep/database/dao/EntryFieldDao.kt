package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.keep.database.model.entry.EntryFieldsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryFieldDao {

    @Query("SELECT * FROM entry_fields")
    fun getEntryFields(): Flow<List<EntryFieldsEntity>>

    @Upsert(entity = EntryFieldsEntity::class)
    fun upsertEntryFields(entryFields: EntryFieldsEntity)
}