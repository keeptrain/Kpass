package com.keep.model

data class Field(
    val id: Int = 0,
    val fieldName: String,
    val isDefault: Boolean = false,
    val showInNewEntry: Boolean = false
)