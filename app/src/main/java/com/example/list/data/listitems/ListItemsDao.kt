package com.example.list.data.listitems

import androidx.room.Dao
import androidx.room.Query
import com.example.list.data.firebase.items.Items
import com.example.list.data.list.ListEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ListItemsDao {

    @Query("SELECT * FROM `list_table` WHERE listCreatorId = :listId")
    abstract suspend fun getListWithItems(listId: Int): ListItemsEntity

    @Query("SELECT * FROM item_table WHERE listID = :listId")
    abstract suspend fun getItemsForList(listId: Int): List<Items>
}
