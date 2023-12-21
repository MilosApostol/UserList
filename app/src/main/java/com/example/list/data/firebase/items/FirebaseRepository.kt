package com.example.list.data.firebase.items

import com.example.list.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FirebaseRepository {

    private var job: Job? = null

    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference(Constants.Items)
    private val _itemsFlow = MutableStateFlow<List<Items>>(emptyList())

    init {
        fetchData()
    }

    fun getItemsFlow(): StateFlow<List<Items>> {
        return _itemsFlow
    }

    fun fetchData() {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemsList = mutableListOf<Items>()
                for (itemSnapshot in snapshot.children) {
                    val item: Items? = itemSnapshot.getValue(Items::class.java)
                    item?.let { itemsList.add(it) }
                }
                _itemsFlow.value = itemsList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        job = Job()

        job?.let { current ->
            databaseReference.addValueEventListener(valueEventListener)

            current.invokeOnCompletion {
                databaseReference.removeEventListener(valueEventListener)
            }
        }
    }

    fun cancelFetch() {
        job?.cancel()
    }

    fun removeItem(itemId: String) {
        databaseReference.child(itemId).removeValue()
    }

    fun removeItems(itemCreatorId: String) {
        val query = databaseReference.orderByChild("itemCreatorId")
            .equalTo(itemCreatorId)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (itemSnapshot in snapshot.children) {
                    itemSnapshot.ref.removeValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}