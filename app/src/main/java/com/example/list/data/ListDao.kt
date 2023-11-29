package com.example.list.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ListDao {

    @Query("SELECT * FROM list_table")
    suspend fun getAll(): Flow<ListEntity>

    @Query("SELECT * FROM list_table WHERE id = :listId")
    suspend fun getListId(listId: Int): ListEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: ListEntity)

    @Update
    suspend fun updateList(list: ListEntity)

    @Delete
    suspend fun deleteList(list: ListEntity)


}