package com.example.list.items

import com.example.list.data.Items
import com.example.list.data.ListEntity
import com.google.firebase.database.DatabaseReference

fun loadDatabase(firebaseData: DatabaseReference) {
    val availableSalads: List<Items> = mutableListOf(
        Items(itemName = "tuc"),
        Items(itemName ="Lettuce"),
        Items(itemName ="Tomato"),
        Items(itemName ="Zucchini")
    )
    availableSalads.forEach {
        val key = firebaseData.child("items").push().key
        if (key != null) {
            it.itemId = key
        }
        if (key != null) {
            firebaseData.child("salads").child(key).setValue(it)
        }
    }
}