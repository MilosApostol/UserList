package com.example.list.data

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ListRepository
) : ViewModel() {
    private val _lists = mutableStateListOf<ListEntity>()
    val lists: List<ListEntity> get() = _lists

    fun addList(list: ListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertList(list)
            getAllLists // Refresh the list after insertion
        }
    }

    lateinit var getAllLists: Flow<List<ListEntity>>

    init {
        viewModelScope.launch() {
            getAllLists = repository.getLists()
        }
    }


    suspend fun getListById(listId: Int): Flow<ListEntity> {
        return repository.getListById(listId)
    }

    fun updateList(list: ListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateList(list)
            getAllLists // Refresh the list after update
        }
    }

    fun removeList(list: ListEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteList(list)
            getAllLists//refresh
        }
    }

    fun deleteAllLists() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLists()
        }

    }
}
