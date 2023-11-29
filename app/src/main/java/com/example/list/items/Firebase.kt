package com.example.list.items

import com.example.list.data.Items
import com.example.list.data.ListEntity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage

class FirebaseData(private val database: FirebaseDatabase) {

    companion object {
        @Volatile
        private var instance: FirebaseData? = null

        fun getInstance(): FirebaseData {
            return instance ?: synchronized(this) {
                instance ?: FirebaseData(FirebaseDatabase.getInstance()).also {
                    instance = it
                }
            }
        }
    }

    fun saveItem(items: Items) {
        val newItemReference = database.reference.child("items").push()
        newItemReference.setValue(items)
    }

    fun getItemsForList(listId: String, callback: (List<ListEntity>) -> Unit) {
        val itemsReference =
            database.reference.child("items").orderByChild("listId").equalTo(listId)
        itemsReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<ListEntity>()
                for (childSnapshot in snapshot.children) {
                    val firebaseItem = childSnapshot.getValue(ListEntity::class.java)
                    firebaseItem?.let { items.add(it) }
                }
                callback(items)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors
            }
        })
    }

    fun storage() {
        var storage: FirebaseStorage = Firebase.storage
        var storageRef = storage.reference

    }
}
