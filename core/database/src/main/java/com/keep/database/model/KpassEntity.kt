package com.keep.database.model

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kpass")
data class KpassEntity (

    @PrimaryKey
    @ColumnInfo("kpassId")
    val kpassId: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "website")
    val website: String,
)