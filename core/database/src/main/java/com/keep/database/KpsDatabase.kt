package com.keep.database

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.keep.database.dao.CategoryDao
import com.keep.database.model.CategoryEntity
import com.keep.database.model.KpassEntity

@Database(entities = [
    KpassEntity::class,
    CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KpsDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}

