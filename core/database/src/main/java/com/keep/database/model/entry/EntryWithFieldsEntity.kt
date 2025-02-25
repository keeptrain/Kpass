package com.keep.database.model.entry

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.keep.model.Entry
import com.keep.model.EntryFields

@Entity(tableName = "entry_with_fields")
data class EntryWithFieldsEntity (
    @Embedded val entry: Entry,

    @Relation(
        parentColumn = "id",
        entityColumn = "entryId")
    val fields: List<EntryFields>
)

fun EntryWithFieldsEntity.toExternalModel() = EntryWithFieldsEntity(
    entry = entry,
    fields = fields
)