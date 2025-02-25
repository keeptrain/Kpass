package com.keep.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keep.database.dao.CategoryDao
import com.keep.database.dao.EntryDao
import com.keep.database.model.CategoryEntity
import com.keep.database.model.EntryEntity

@Database(entities = [
    EntryEntity::class,
    CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KpsDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao
    abstract fun categoryDao(): CategoryDao
}

