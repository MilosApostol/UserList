package com.example.list.data.list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.list.data.items.Items
import com.example.list.data.userdata.User

@Entity(tableName = "list_table")
data class ListEntity(
    @ColumnInfo(name = "listCreatorId")
    var listCreatorId: Int = 0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "listId")
    var id: Int = 0,
    @ColumnInfo(name = "list_name")
    var listName: String
)
