package com.keep.database.model

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.keep.model.Category

@Entity(
    tableName = "kpass",
    foreignKeys = [
        ForeignKey(entity = CategoryEntity::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("categoryId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ])
data class KpassEntity (

    @PrimaryKey
    @ColumnInfo("kpassId")
    val kpassId: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("categoryId")
    val categoryId: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "website")
    val website: String,
)