package com.example.list.data.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(private val repository: FirebaseRepository) : ViewModel() {

    lateinit var getAllItems: Flow<List<Items>>

    init {
        viewModelScope.launch {
            getAllItems = repository.getItemsFlow()
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            repository.fetchData()
        }
    }

    fun cancelFetch() {
        repository.cancelFetch()
    }

}