package com.example.list.items

import com.example.list.data.Items
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject


class FirebaseRepository @Inject constructor(private val itemDataSource: ItemDataSource) {

    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("items")

    suspend fun addItem(item: Items) {
        val newItemReference = databaseReference.child(item.itemId)
        newItemReference.setValue(item)
    }

    suspend fun updateItem(item: Items) {
        databaseReference.child(item.itemId).setValue(item)
    }

    suspend fun deleteItem(itemId: String) {
        databaseReference.child(itemId).removeValue()
    }

    suspend fun getItemById(itemId: String): Task<ByteArray> {
        return itemDataSource.getItemById(itemId)

    }
}