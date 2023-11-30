package com.example.list.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.list.data.Items
import com.example.list.items.FirebaseViewModel
import com.google.firebase.storage.FirebaseStorage


@Composable
fun AddItems(
    navController: NavController,
    firebaseViewModel: FirebaseViewModel = hiltViewModel()
) {
    var itemName by remember { mutableStateOf("") }
    val storageReference = FirebaseStorage.getInstance().reference.child("item")
    Column(modifier = Modifier.fillMaxSize()) {

        ListNameTextField(label = "item", value = itemName) { itemName = it }

        Button(onClick = {
            if (itemName.isNotEmpty()) {
                firebaseViewModel.addItem(Items(itemName))
            }
        }) {

        }
    }
}