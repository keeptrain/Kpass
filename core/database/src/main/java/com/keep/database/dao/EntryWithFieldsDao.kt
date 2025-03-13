package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.keep.model.EntryWithFields
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryWithFieldsDao {
    @Transaction
    @Query("SELECT * FROM entry_with_fields")
    fun getEntryWithFields(): Flow<List<EntryWithFields>>
}