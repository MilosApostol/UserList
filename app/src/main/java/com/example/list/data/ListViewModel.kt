package com.example.list.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ListViewModel(private val listDao: ListDao) : ViewModel() {
    private val _lists = mutableStateListOf<ListEntity>()
    val lists: List<ListEntity> get() = _lists

    fun addList(name: String) {
        viewModelScope.launch {
            val newList = ListEntity(list = name)
            listDao.insertList(newList)
            getAllLists()//refresh the list after insertion
        }
    }

    fun getAllLists() {
        viewModelScope.launch {
            _lists.clear()
            _lists.addAll(listDao.getAll())
        }
    }

    suspend fun getListById(listId: Int): List<ListEntity> {
        return listDao.getListId(listId)
    }

    fun updateList(list: ListEntity) {
        viewModelScope.launch {
            listDao.updateList(list)
            getAllLists() // Refresh the list after update
        }
    }

    fun removeList(listId: Int) {
        viewModelScope.launch {
            val listToRemove = listDao.getListId(listId)
            listToRemove?.let {
                listDao.deleteList(it)
                getAllLists()//refresh
            }
        }
    }
}