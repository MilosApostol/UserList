package com.example.list.userdata

import androidx.compose.ui.input.pointer.PointerEventPass
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
    abstract fun getUserById(userId: Int): User
    @Query("SELECT * FROM `user_table`")
    abstract fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM `user_table` WHERE name = :userName")
    abstract fun getUserByUsername(userName: String): User

    @Query("SELECT * FROM `user_table` WHERE name = :userName AND password = :password")
    abstract fun getUserByNameAndPass(userName: String, password: String): User

    @Query("SELECT EXISTS(SELECT * FROM `user_table` WHERE name =:userName AND password =:password)")
    abstract fun login (userName: String, password: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM `user_table` WHERE name =:userName)")
    abstract fun userExist (userName: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(user: User)

    @Update
    abstract suspend fun updateUser(user: User)

    @Delete
    abstract suspend fun deleteUser(user: User)

}