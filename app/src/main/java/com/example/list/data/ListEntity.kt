package com.example.list.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: Int? = null,
    var name: String = "",
    var password: String = "",
)

@Entity(tableName = "list_table")
data class ListEntity(
    @ColumnInfo(name = "listCreatorId")
    var listCreatorId: Int? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "listId")
    var id: Int = 0,
    @ColumnInfo(name = "list_name")
    var listName: String
)
data class UserWithList(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "itemId"
    )
    val list: List<ListEntity>
)
data class Items(
    var itemId: String = "", // Unique identifier for Firebase
    var itemCreatorId: Int = 0, // List identifier in Firebase
    val itemName: String = ""
)

data class ListWithItem(
    @Embedded val lists: List<ListEntity>,
    @Relation(
        parentColumn = "userId",
        entityColumn = "listCreatorId"
    )
    val items: List<Items>
)
