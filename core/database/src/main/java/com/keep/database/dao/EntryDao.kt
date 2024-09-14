package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keep.database.model.KpassEntity

@Dao
interface EntryDao {

    @Query("SELECT categoryId FROM kpass")
    suspend fun getCategoryList() : List<KpassEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEntry(entryEntities: KpassEntity)
}