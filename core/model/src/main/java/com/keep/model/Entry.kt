package com.keep.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entry(
    val id: Int? = null,
    val title: String,
    val categoryId: Int,
) : Parcelable