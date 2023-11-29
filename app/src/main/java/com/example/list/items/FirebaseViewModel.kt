package com.example.list.items

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.data.Items
import com.example.list.loginout.ListSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val listSession: ListSessionManager
) : ViewModel() {

    private val _allItems = mutableStateListOf<Items>()
    val allItems: List<Items> get() = _allItems

    fun addItem(item: Items) {
        item.itemCreatorId = listSession.getListId()!!
        viewModelScope.launch {
            firebaseRepository.addItem(item)
        }
    }

    fun updateItem(item: Items) {
        item.itemCreatorId = listSession.getListId()!!
        viewModelScope.launch {
            firebaseRepository.updateItem(item)
        }
    }

    fun deleteItem(itemsId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseRepository.deleteItem(itemsId)
        }
    }
    /*
        fun getItemById(itemsId: String): Flow<Items> = flow {
            val task = firebaseRepository.getItemById(itemsId)

            try {
                val byteArray = suspendCoroutine<ByteArray> { continuation ->
                    task.addOnSuccessListener { snapshot ->
                        continuation.resume(snapshot)
                    }.addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
                }

                val items =
                    parseByteArrayToItems(byteArray) // Adjust this function based on your data model
                emit(items)
                _allItems.add(items)
            } catch (e: Exception) {

            }
        }

     */
}

