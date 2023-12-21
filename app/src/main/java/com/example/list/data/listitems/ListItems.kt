package com.example.list.data.listitems

import androidx.lifecycle.ViewModel
import com.example.list.data.list.ListEntity
import com.example.list.data.userlists.UserListsRepository
import com.example.list.sessionmanager.ListSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class ListItemsViewModel @Inject constructor(
    private val userListRepository: UserListsRepository,
    private val listSessionManager: ListSessionManager
) : ViewModel() {

    fun getItemByListId(): Flow<List<ListEntity>> {
        return listSessionManager.getList()?.listCreatorId?.let { userListRepository.getUsersListById(it) }
            ?: emptyFlow()
    }

    fun getList(): ListEntity? {
        return listSessionManager.getList()
    }
}