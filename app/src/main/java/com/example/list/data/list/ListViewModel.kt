package com.example.list.data.list

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.Constants
import com.example.list.data.ProvideNetworkState
import com.example.list.data.firebase.items.Items
import com.example.list.sessionmanager.UserSessionManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
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


    val listFlow = MutableStateFlow<List<ListEntity>>(emptyList())

    fun addList(list: ListEntity) {
//        list.listCreatorId = userSession.getUserId()!!
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertList(list)
        }
    }

    lateinit var getAllLists: Flow<List<ListEntity>>

    init {
        viewModelScope.launch {
            getAllLists = flow {
                repository.fetchData()
                emitAll(repository.getLists())
            }
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            repository.fetchData()
        }
    }


    fun getListById(listId: Int): Flow<ListEntity> {
        return repository.getListById(listId)
    }

    fun updateList(list: ListEntity) {
        list.listCreatorId = userSession.getUserId().toString()!!
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
