package com.keep.model

import java.io.Serializable


data class Category(
    val id: Int? = null,
    val name: String,
) : Serializable