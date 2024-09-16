package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keep.database.model.EntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {

    @Query("SELECT categoryId FROM entry")
    suspend fun getCategoryList() : Flow<List<EntryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEntry(entryEntities: EntryEntity)
}