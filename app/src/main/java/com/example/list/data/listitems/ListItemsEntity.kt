package com.example.list.data.listitems

import androidx.room.Embedded
import androidx.room.Relation
import com.example.list.data.firebase.items.Items
import com.example.list.data.list.ListEntity

data class ListItemsEntity(
    @Embedded val list: ListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "listId"
    )
    val items: List<Items>
)