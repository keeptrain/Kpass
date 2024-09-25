package com.keep.model

import java.io.Serializable
import java.util.UUID

data class Category(
    val id: String = "category-${UUID.randomUUID()}",
    val name: String
) : Serializable
