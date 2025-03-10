package com.keep.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keep.database.dao.CategoryDao
import com.keep.database.dao.EntryDao
import com.keep.database.dao.EntryFieldDao
import com.keep.database.dao.FieldDao
import com.keep.database.model.CategoryEntity
import com.keep.database.model.FieldEntity
import com.keep.database.model.entry.EntryEntity
import com.keep.database.model.entry.EntryFieldEntity

@Database(entities = [
    EntryEntity::class,
    CategoryEntity::class,
    FieldEntity::class,
    EntryFieldEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class KpsDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao
    abstract fun categoryDao(): CategoryDao
    abstract fun fieldDao(): FieldDao
    abstract fun entryFieldDao(): EntryFieldDao
}