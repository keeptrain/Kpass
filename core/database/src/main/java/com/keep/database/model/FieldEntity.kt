package com.keep.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keep.model.Field

@Entity(tableName = "field")
data class FieldEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("fieldName")
    val fieldName: String,

    @ColumnInfo("isDefault")
    val isDefault: Boolean = false,

    @ColumnInfo("showInNewEntry")
    val showInNewEntry: Boolean = false
)

fun FieldEntity.toExternalModel() = Field(
    id = id,
    fieldName = fieldName,
    isDefault = isDefault,
    showInNewEntry = showInNewEntry
)