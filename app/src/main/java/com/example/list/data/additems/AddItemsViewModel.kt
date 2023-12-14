package com.example.list.data.additems

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCustomViewModel @Inject constructor(
    private val repository: AddItemsRepository,
) : ViewModel() {

    fun addItem(item: AddItemsCustom) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertItem(item)
        }
    }

    lateinit var getAllItems: Flow<List<AddItemsCustom>>

    init {
        viewModelScope.launch {
            getAllItems = repository.getItems()
        }
    }

    fun getItemById(itemId: Int): Flow<AddItemsCustom> {
        return repository.getItemsById(itemId)
    }

    fun removeItem(item: AddItemsCustom) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }
    }
}