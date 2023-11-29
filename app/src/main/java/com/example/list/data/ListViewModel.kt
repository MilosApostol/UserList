package com.example.list.data

import androidx.compose.runtime.MutableState
<<<<<<< HEAD
import androidx.compose.runtime.State
=======
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<< HEAD
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.list.ListEvent
import com.example.list.ListState
import com.example.list.use_case.ListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
=======
import com.example.list.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
<<<<<<< HEAD
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
=======
    private val repository: ListRepository
) : ViewModel() {

    var listNameState by mutableStateOf("")
    private val _lists = mutableStateListOf<ListEntity>()
    val lists: List<ListEntity> get() = _lists
    private val _currentScreen: MutableState<Screen> =
        mutableStateOf(Screen.DrawerScreen.Add)
    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun onListNameChanged(newListName: String){
        listNameState = newListName
    }
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


     fun getListById(listId: Int): Flow<ListEntity> {
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
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
<<<<<<< HEAD
}

 */
=======

    fun deleteAllLists() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLists()
            getAllLists
        }

    }

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
