package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.keep.database.model.entry.EntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {

    @Query("SELECT * FROM entry")
    fun getEntryEntity() : Flow<List<EntryEntity>>

    @Upsert(entity = EntryEntity::class)
    suspend fun upsertEntry(entryEntity: EntryEntity)

    @Query("DELETE FROM entry WHERE id = :id")
    suspend fun deleteEntry(id: Int)

}