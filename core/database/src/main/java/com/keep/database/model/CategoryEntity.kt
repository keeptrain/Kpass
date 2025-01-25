package com.keep.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keep.model.Category

@Entity(tableName = "category")
data class CategoryEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("categoryId")
    val id: Int?,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("position")
    val position: Int

)

fun CategoryEntity.toExternalModel() = Category(
    id = id,
    name = name,
    position = position
)