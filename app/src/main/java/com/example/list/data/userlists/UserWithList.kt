package com.example.list.data.userlists

import androidx.room.Embedded
import androidx.room.Relation
import com.example.list.data.list.ListEntity
import com.example.list.data.userdata.User

data class UserWithList(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "itemId"
    )
    val list: List<ListEntity>
)