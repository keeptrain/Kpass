package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keep.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getCategoryEntity(): Flow<List<CategoryEntity>>


    @Query("SELECT COUNT(*) FROM category")
    suspend fun getCategoryCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(categoryEntities: CategoryEntity)

    @Delete
    fun deleteCategory(categoryEntities: CategoryEntity)
}