package com.kpass.newentry.adapter

import com.keep.model.EntryFields

sealed class EntryFieldAdapterItem (val viewType : EnumEntryFieldViewType) {
    class EntryFieldsItem(val entryFields: EntryFields) : EntryFieldAdapterItem(EnumEntryFieldViewType.ENTRY_FIELDS)
    class EmptyItem : EntryFieldAdapterItem(EnumEntryFieldViewType.EMPTY)
}

enum class EnumEntryFieldViewType{
    ENTRY_FIELDS,
    EMPTY;

     companion object {
        fun getEnumByOrdinal(index: Int) : EnumEntryFieldViewType {
            return entries[index]
        }
    }
}