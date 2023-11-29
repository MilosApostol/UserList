package com.example.list.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class ListDao {

<<<<<<< HEAD
    @Query("SELECT * FROM list_table")
    suspend fun getAll(): Flow<ListEntity>

    @Query("SELECT * FROM list_table WHERE id = :listId")
    suspend fun getListId(listId: Int): ListEntity
=======
    @Query("SELECT * FROM `list_table`")
    abstract fun getAll(): Flow<List<ListEntity>>

    @Query("SELECT * FROM 'list_table' WHERE listId = :listId")
    abstract fun getListId(listId: Int): Flow<ListEntity>
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertList(list: ListEntity)

    @Update
    abstract suspend fun updateList(list: ListEntity)

    @Delete
    abstract suspend fun deleteList(list: ListEntity)

    @Query("DELETE FROM `list_table`")
    suspend fun deleteAllLists() {
    }
}
