package com.keep.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Category")
data class CategoryEntity (

    @PrimaryKey
    @ColumnInfo("categoryId")
    val id: String,

    @ColumnInfo("name",)
    val name: String,


)