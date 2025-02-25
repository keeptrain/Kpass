package com.keep.database.model.entry

import androidx.room.Entity
import androidx.room.ForeignKey
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
    val entryId: Int,
    val fieldName: String,
    val fieldValue: String,
)

fun EntryFieldsEntity.toExternalModel() = EntryFields(
    entryId = entryId,
    fieldName = fieldName,
    fieldValue = fieldValue
)
