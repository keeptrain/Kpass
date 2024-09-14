package com.keep.database.model

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.keep.model.Category
import com.keep.model.Entry

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

fun KpassEntity.toExternalModel() = Entry (
    id = kpassId,
    categoryId = categoryId,
    title = title,
    username = username,
    password = password,
    website = website
)