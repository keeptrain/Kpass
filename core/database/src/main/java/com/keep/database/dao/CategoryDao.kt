package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.keep.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category ORDER BY position ASC")
    fun getCategoryEntity(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntities: CategoryEntity)

    @Update
    suspend fun updateCategory(categoryEntities: CategoryEntity)

    @Query("SELECT MAX(position) FROM category")
    suspend fun getLastPosition() : Int?

    @Query("UPDATE category SET position = :position WHERE categoryId = :id")
    suspend fun updatePosition(id: Int, position: Int)

    @Delete
    suspend fun deleteCategory(categoryEntities: CategoryEntity)
}