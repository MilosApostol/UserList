package com.example.list.data.list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.data.ProvideNetworkState
import com.example.list.sessionmanager.UserSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ListRepository,
    private val userSession: UserSessionManager
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _networkState = MutableStateFlow<Boolean>(false)
    val networkState = _networkState.asStateFlow()

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
            delay(3000)
            _isLoading.value = false
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

    fun onNetworkRestored() {
        lateinit var getAllLists: Flow<List<ListEntity>>
        viewModelScope.launch() {
            getAllLists = repository.getLists()
            delay(3000)
            _isLoading.value = false
        }
    }

    fun updateNetworkState(isNetworkAvailable: Boolean) {
        _networkState.value = isNetworkAvailable
    }
}
