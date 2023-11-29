package com.example.list.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.list.ListEvent
import com.example.list.ListState
import com.example.list.use_case.ListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: ListUseCase
) : ViewModel() {

    val _lists = mutableStateOf(ListState())
    val lists: State<ListState> = _lists

    private var recentlyDeleted: ListEntity? = null

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.DeleteList -> {
                viewModelScope.launch {
                    useCase.deleteList(event.list)
                    recentlyDeleted = event.list
                }
            }

            is ListEvent.RestoreList -> {
                viewModelScope.launch {
                    useCase.addList(recentlyDeleted ?: return@launch)
                    recentlyDeleted = null
                }
            }

            is ListEvent.AddList -> {
                viewModelScope.launch {
                    useCase.addList(event.list)
                }
            }
        }
    }
}
/*
    fun addList(name: ListEntity) {
        viewModelScope.launch {
            repository.insertList(name)
            getAllLists()//refresh the list after insertion
        }
    }

    fun getAllLists() {
        viewModelScope.launch {
            repository.getLists()
        }
    }

    suspend fun getListById(listId: Int): ListEntity {
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
            repository.deleteList(listId)
            getAllLists()//refresh
        }
    }
}

 */