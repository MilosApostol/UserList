package com.example.list.data.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.sessionmanager.UserSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ListRepository,
    private val userSession: UserSessionManager
) : ViewModel() {

    private val _lists = mutableStateListOf<ListEntity>()
    val lists: List<ListEntity> get() = _lists
    fun addList(list: ListEntity) {
        list.listCreatorId = userSession.getUserId()!!
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertList(list)
        }
    }

    lateinit var getAllLists: Flow<List<ListEntity>>
    init {
        viewModelScope.launch() {
            getAllLists = repository.getLists()
        }
    }
     fun getListById(listId: Int): Flow<ListEntity> {
        return repository.getListById(listId)
    }
    fun updateList(list: ListEntity) {
        list.listCreatorId = userSession.getUserId()!!
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateList(list)
        }
    }

    fun removeList(list: ListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteList(list)
        }
    }
    fun deleteAllLists() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLists()
        }

    }
}