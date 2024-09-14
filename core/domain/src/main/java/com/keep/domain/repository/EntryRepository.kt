package com.keep.domain.repository

import com.keep.model.Entry

interface EntryRepository {
    fun getEntry() : List<Entry>
    fun getCategoryList() : List<Entry>
    suspend fun insertEntry(entry: Entry)

}