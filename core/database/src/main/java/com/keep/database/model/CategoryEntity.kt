package com.keep.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keep.model.Category
import java.util.UUID


@Entity(tableName = "category")
data class CategoryEntity (

    @PrimaryKey
    @ColumnInfo("categoryId")
    val id: String,

    @ColumnInfo("name",)
    val name: String,


)

fun CategoryEntity.toExternalModel() = Category(
    id = id,
    name = name
)