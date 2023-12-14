package com.example.list.data.additems

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AddItemsDao {

    @Query("SELECT * FROM `add_items_table`")
    abstract fun getAll(): Flow<List<AddItemsCustom>>

    @Query("SELECT * FROM `add_items_table` WHERE id = :id")
    abstract fun getItemById(id: Int): Flow<AddItemsCustom>


    @Insert(onConflict =OnConflictStrategy.REPLACE)
    abstract suspend fun insertItem(item: AddItemsCustom)

    @Delete
    abstract suspend fun deleteItem(item: AddItemsCustom)
}
