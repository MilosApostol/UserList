package com.example.list.userdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.list.data.User
import com.example.list.data.UserWithList
import kotlinx.coroutines.flow.Flow


@Dao
abstract class UserDao {
    @Query("SELECT * FROM 'user_table' WHERE userId = :userId")
    abstract fun getUserById(userId: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(user: User)

    @Update
    abstract suspend fun updateUser(user: User)

    @Delete
    abstract suspend fun deleteUser(user: User)

    //getting user with lists
    @Transaction
    @Query("SELECT * FROM `user_table`")
    abstract fun getUsersWithPlaylists(): List<UserWithList>
}