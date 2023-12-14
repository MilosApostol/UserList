package com.example.list.data.api.additemsapi

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemsViewModel @Inject constructor(
    private val repository: AddItemsRepository,
    private val useCase: AddItemsUseCase
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private var _selectedItem = mutableStateListOf<AddItemsData?>(null)
    val selectedItem: List<AddItemsData?> get() = _selectedItem


    private val _addItem = MutableStateFlow(listOf<AddItemsData>())

    val addItem = searchText
        .combine(_addItem) { text, addItem ->
            if (text.isBlank()) {
                addItem
            } else {
                addItem.filter { item ->
                    item.title.contains(text, ignoreCase = true)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _addItem.value
        )

    fun getItems() {
        viewModelScope.launch {
            _addItem.value = useCase.getItems()
        }
    }
    fun getItem(title: String) {
        viewModelScope.launch {
            _addItem.value = listOf(useCase.getItem(title))
        }
    }

    fun addToSelectedItems(items: AddItemsData) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingItem = selectedItem.find { it?.title == items.title }
            existingItem?.let {
                it.title = items.title
                it.description = items.description
                it.price = items.price
            } ?: run {
                _selectedItem.addAll(listOf(items))
            }
        }
    }

    fun onSearchChange(text: String) {
        _searchText.value = text
    }


}