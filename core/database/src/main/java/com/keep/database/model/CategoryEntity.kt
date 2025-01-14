package com.keep.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keep.model.Category
import java.io.Serializable

@Entity(tableName = "category")
data class CategoryEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("categoryId")
    val id: Int? = 1,

    @ColumnInfo("name")
    val name: String,

    ) : Serializable
//{
//    companion object {
//        private var counter = 0
//
//        fun create(name: String): CategoryEntity {
//            counter++
//            return CategoryEntity(id = "category-$counter", name = name)
//        }
//    }
//}

fun CategoryEntity.toExternalModel() = Category(
    id = id,
    name = name
)