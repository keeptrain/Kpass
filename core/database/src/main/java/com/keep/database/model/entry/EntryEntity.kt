package com.keep.database.model.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.keep.database.model.CategoryEntity
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
    @ColumnInfo("id")
    val id: Int?,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("categoryId", index = true)
    val categoryId: Int,
    )

fun EntryEntity.toExternalModel() = Entry (
    id = id,
    title = title,
    categoryId = categoryId,
)