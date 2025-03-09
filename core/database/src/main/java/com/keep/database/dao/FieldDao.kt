package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.keep.database.model.FieldEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldDao {
    @Query("SELECT * FROM field")
    fun getFieldEntity(): Flow<List<FieldEntity>>

    @Upsert(entity = FieldEntity::class)
    suspend fun upsertField(field: FieldEntity)
}