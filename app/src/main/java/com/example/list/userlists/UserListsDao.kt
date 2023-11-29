package com.example.list.userlists

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.list.data.ListEntity
import com.example.list.data.UserWithList
import kotlinx.coroutines.flow.Flow


@Dao
abstract class UserListsDao {

    @Query("SELECT * FROM `list_table` WHERE listCreatorId = :userId")
    abstract fun getListsByUserId(userId: Int): Flow<List<ListEntity>>
}
