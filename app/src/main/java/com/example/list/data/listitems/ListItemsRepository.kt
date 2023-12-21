package com.example.list.data.listitems

import com.example.list.data.list.ListEntity
import com.example.list.data.userlists.UserListsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListsRepository @Inject constructor(
    private val listItemsDao: ListItemsDao,
) {
    suspend fun getUsersListById(listId: Int): ListItemsEntity {
        return listItemsDao.getListWithItems(listId)
    }
}
