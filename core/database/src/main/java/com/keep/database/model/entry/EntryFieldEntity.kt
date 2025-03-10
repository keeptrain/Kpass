package com.keep.database.model.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.keep.database.model.FieldEntity
import com.keep.model.EntryField

@Entity(
    tableName = "entry_field",
    primaryKeys = ["entryId"],
    foreignKeys = [
        ForeignKey(entity = EntryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("entryId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FieldEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("fieldId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EntryFieldEntity(
    @ColumnInfo("entryId")
    val entryId: Int,

    @ColumnInfo("fieldId", index = true)
    val fieldId: Int,

    @ColumnInfo("fieldValue")
    val fieldValue: String,
)

fun EntryFieldEntity.toExternalModel() = EntryField(
    entryId = entryId,
    fieldId = fieldId,
    fieldValue = fieldValue
)