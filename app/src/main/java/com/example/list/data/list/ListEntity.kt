package com.example.list.data.list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_table")
data class ListEntity(
    @ColumnInfo(name = "listCreatorId")
    var listCreatorId: String = "",
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "listId")
    var id: String = "",
    @ColumnInfo(name = "list_name")
    var listName: String = ""
)
