package com.example.list.userlists

import com.example.list.data.ListDao
import com.example.list.data.ListEntity
import com.example.list.data.User
import com.example.list.data.UserWithList
import com.example.list.navigation.Screen
import com.example.list.userdata.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserListsRepository @Inject constructor(
    private val userListsDao: UserListsDao,
) {
    fun getUsersListById(userId: Int): Flow<List<ListEntity>> {
        return userListsDao.getListsByUserId(userId)
    }
}
