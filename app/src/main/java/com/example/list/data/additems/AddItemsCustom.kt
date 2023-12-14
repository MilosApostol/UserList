package com.example.list.data.additems

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "add_items_table")
data class AddItemsCustom(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val price: String = ""
)