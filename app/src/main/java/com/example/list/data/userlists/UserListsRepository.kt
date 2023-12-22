package com.example.list.data.userlists

import com.example.list.data.list.ListEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListsRepository @Inject constructor(
    private val userListsDao: UserListsDao,
) {
    fun getUsersListById(userId: String): Flow<List<ListEntity>> {
        return userListsDao.getListsByUserId(userId)
    }
}
