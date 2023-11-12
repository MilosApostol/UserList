package com.example.list.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(private val repository: Repository) : ViewModel() {
    private val _lists = mutableStateListOf<ListEntity>()
    val lists: List<ListEntity> get() = _lists

    private var recentlyDeleted: ListEntity? = null

    fun addList(name: String) {
        viewModelScope.launch {
            val newList = ListEntity(listName = name)
            repository.insertList(newList)
            getAllLists()//refresh the list after insertion
        }
    }

    fun getAllLists() {
        viewModelScope.launch {
            _lists.clear()
            repository.getLists()
        }
    }

    suspend fun getListById(listId: Int): List<ListEntity> {
        return repository.getListById(listId)
    }

    fun updateList(list: ListEntity) {
        viewModelScope.launch {
            repository.updateList(list)
            getAllLists() // Refresh the list after update
        }
    }

    fun removeList(listId: ListEntity) {
        viewModelScope.launch {
            val listToRemove = repository.insertList(listId)
            repository.deleteList(listId)
            getAllLists()//refresh
        }
    }
}