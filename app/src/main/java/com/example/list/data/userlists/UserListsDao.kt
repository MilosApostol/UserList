package com.example.list.data.userlists

import androidx.room.Dao
import androidx.room.Query
import com.example.list.data.list.ListEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class UserListsDao {

    @Query("SELECT * FROM `list_table` WHERE listCreatorId = :userId")
    abstract fun getListsByUserId(userId: String): Flow<List<ListEntity>>
}
