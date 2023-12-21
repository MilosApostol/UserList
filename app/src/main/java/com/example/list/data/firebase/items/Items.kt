package com.example.list.data.firebase.items

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item_table")
data class Items(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "itemId")
    var itemId: String = "",
    @ColumnInfo(name = "listID")
    var itemCreatorId: String = "",
    val itemName: String = ""
)
