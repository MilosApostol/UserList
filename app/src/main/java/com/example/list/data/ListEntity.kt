package com.example.list.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_table")
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val uid: Int = 0,
    @ColumnInfo(name = "list_name")
    val listName: String?
)

class InvalidListException(message: String) : Exception(message)
