package com.keep.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keep.database.model.CategoryEntity

@Dao
interface CategoryDao {
    @Query(
        "SELECT * FROM category",
    )
    fun getCategoryEntity(): List<CategoryEntity>

    @Insert
    fun insertCategory(categoryEntities: CategoryEntity)

    @Delete
    fun deleteCategory(categoryEntities: CategoryEntity)
}