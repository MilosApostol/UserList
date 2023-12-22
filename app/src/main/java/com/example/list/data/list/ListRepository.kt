package com.example.list.data.list

import com.example.list.Constants
import com.example.list.data.firebase.items.Items
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class ListRepository @Inject constructor(private val dao: ListDao) {

    private var job: Job? = null

    val reference = FirebaseDatabase.getInstance().getReference(Constants.Lists)
       .child(FirebaseAuth.getInstance().currentUser?.uid ?: "")

    private val _listFlow = MutableStateFlow<List<ListEntity>>(emptyList())

    suspend fun getLists(): StateFlow<List<ListEntity>> {
        return _listFlow
    }

    fun getListById(id: Int): Flow<ListEntity> {
        return dao.getListId(id)
    }

    suspend fun insertList(list: ListEntity) {
        dao.insertList(list)
    }

    suspend fun updateList(list: ListEntity) {
        dao.updateList(list)
    }

    suspend fun deleteList(list: ListEntity) {
        dao.deleteList(list)
    }

    suspend fun deleteAllLists() {
        dao.deleteAllLists()
    }

    fun fetchData() {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newList = mutableListOf<ListEntity>()
                try {
                    for (itemSnapshot in snapshot.children) {
                        val list: ListEntity? = itemSnapshot.getValue(ListEntity::class.java)
                        list?.let { newList.add(it) }
                    }
                } catch (e: DatabaseException) {
                    // Handle the error gracefully, e.g., log a message
                }
                _listFlow.value = newList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle cancellation appropriately
            }
        }

        job = Job()

        job?.let { current ->
            reference.addValueEventListener(valueEventListener)

            current.invokeOnCompletion {
                if (it is CancellationException) {
                    // Handle cancellation logic, e.g., log a message
                }
                reference.removeEventListener(valueEventListener)
            }
        }
    }

}