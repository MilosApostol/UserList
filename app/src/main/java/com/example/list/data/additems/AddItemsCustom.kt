package com.example.list.data.additems

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "add_items_table")
data class AddItemsCustom(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var price: String = ""
)