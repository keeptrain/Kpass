package com.keep.model

data class EntryWithFields(
    val entry: Entry,
    val fields: List<EntryField>
)