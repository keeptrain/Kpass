package com.keep.database.model.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.keep.model.EntryFields

@Entity(
    tableName = "entry_fields",
    foreignKeys = [
        ForeignKey(entity = EntryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("entryId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ])
data class EntryFieldsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("entryId")
    val entryId: Int,

    @ColumnInfo("fieldName")
    val fieldName: String,

    @ColumnInfo("fieldValue")
    val fieldValue: String,
)

fun EntryFieldsEntity.toExternalModel() = EntryFields(
    entryId = entryId,
    fieldName = fieldName,
    fieldValue = fieldValue
)
