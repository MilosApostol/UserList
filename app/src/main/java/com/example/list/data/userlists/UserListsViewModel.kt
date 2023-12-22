package com.example.list.data.userlists

import androidx.lifecycle.ViewModel
import com.example.list.data.list.ListEntity
import com.example.list.data.userdata.User
import com.example.list.sessionmanager.UserSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class UserListsViewModel @Inject constructor(
    private val userListRepository: UserListsRepository,
    private val userSessionManager: UserSessionManager
) : ViewModel() {

    fun getListsByUserId(): Flow<List<ListEntity>> {
        return userSessionManager.getUser()?.userId?.let { userListRepository.getUsersListById(it.toString()) }
            ?: emptyFlow()
    }

    fun getUser(): User? {
        return userSessionManager.getUser()
    }
}