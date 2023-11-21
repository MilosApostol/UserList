package com.example.list.userdata

import com.example.list.data.User
import com.example.list.data.UserWithList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val dao: UserDao) {

    suspend fun getUsersWithLists(): List<UserWithList> {
        return dao.getUsersWithPlaylists()
    }

    fun getUserById(id: Int): Flow<User>{
        return dao.getUserById(id)
    }
    suspend fun insertUser(user: User){
        dao.insertUser(user)
    }
    suspend fun updateUser(user: User){
        dao.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        dao.deleteUser(user)
    }
}