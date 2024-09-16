package com.keep.database.model

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.keep.model.Category
import com.keep.model.Entry

@Entity(
    tableName = "entry",
    foreignKeys = [
        ForeignKey(entity = CategoryEntity::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("categoryId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ])
data class EntryEntity (

    @PrimaryKey
    @ColumnInfo("entryId")
    val entryId: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("categoryId", index = true)
    val categoryId: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "website")
    val website: String,
)

fun EntryEntity.toExternalModel() = Entry (
    id = entryId,
    categoryId = categoryId,
    title = title,
    username = username,
    password = password,
    website = website
)