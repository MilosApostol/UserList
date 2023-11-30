package com.example.list.data.itemlists

import androidx.room.Embedded
import androidx.room.Relation
import com.example.list.data.list.ListEntity
import com.example.list.data.items.Items

data class ListWithItem(
    @Embedded val lists: List<ListEntity>,
    @Relation(
        parentColumn = "userId",
        entityColumn = "listCreatorId"
    )
    val items: List<Items>
)
