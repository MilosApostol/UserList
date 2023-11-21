package com.example.list.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId: Int,
    val name: String,
    val password: String
)

@Entity(tableName = "list_table")
data class ListEntity(
    @ColumnInfo(name = "listCreatorId")
    val listCreatorId: Int = 0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "listId")
    val id: Int = 0,
    @ColumnInfo(name = "list_name")
    var listName: String
)
data class UserWithList(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "listCreatorId"
    )
    val list: List<ListEntity>
)
