package com.example.list.userlists

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.data.ListDao
import com.example.list.data.ListEntity
import com.example.list.data.User
import com.example.list.data.UserWithList
import com.example.list.loginout.UserSessionManager
import com.example.list.userdata.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListsViewModel @Inject constructor(
    private val userListRepository: UserListsRepository,
    private val userSessionManager: UserSessionManager
) : ViewModel() {

    fun getListsByUserId(): Flow<List<ListEntity>> {
        return userSessionManager.getUser()?.userId?.let { userListRepository.getUsersListById(it) }
            ?: emptyFlow()
    }
}