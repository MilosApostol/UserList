package com.example.list.userdata

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.list.data.UserWithList
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserWithListsDao {

    @Transaction
    @Query("SELECT * FROM user_table WHERE userId = :userId")
    abstract fun getUserWithLists(userId: Int): Flow<UserWithList>

    // Additional queries or operations related to UserWithLists can go here
}