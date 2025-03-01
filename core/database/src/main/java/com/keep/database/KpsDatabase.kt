package com.keep.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keep.database.dao.CategoryDao
import com.keep.database.dao.EntryDao
import com.keep.database.dao.EntryFieldDao
import com.keep.database.model.CategoryEntity
import com.keep.database.model.entry.EntryEntity
import com.keep.database.model.entry.EntryFieldsEntity

@Database(entities = [
    EntryEntity::class,
    CategoryEntity::class,
    EntryFieldsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KpsDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao
    abstract fun categoryDao(): CategoryDao
    abstract fun entryFieldDao(): EntryFieldDao
}