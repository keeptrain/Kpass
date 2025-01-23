package com.keep.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int? = null,
    val name: String,
    val position: Int
) : Parcelable