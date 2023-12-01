package com.example.list.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.Constants
import com.example.list.data.items.Items
import com.example.list.navigation.Screen
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.UUID


@Composable
fun AddItems(
    id: Int,
    navController: NavController = rememberNavController(),
) {
    var itemName by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ListNameTextField(
            label = "item",
            value = itemName,
            onValueChanged = { itemName = it }
        )
        Button(onClick = {
            if (itemName.isNotEmpty()) {
                val newItem = Items(
                    itemId = UUID.randomUUID().toString(),
                    itemCreatorId = id,
                    itemName = itemName
                )
                addItem(newItem,
                    onSuccess = {
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                        navController.navigate(Screen.DrawerScreen.ItemsScreen.route + "/$id")
                    },
                    onFailure = {
                        Toast.makeText(context, "Failure: ", Toast.LENGTH_LONG).show()
                    }
                )
            } else {
                Toast.makeText(context, "Item name is empty", Toast.LENGTH_LONG).show()
            }
        }) {
            Text(text = "add item")
        }
    }
}

fun addItem(item: Items, onSuccess: () -> Unit, onFailure: () -> Unit) {
    val db = Firebase.firestore

    db.collection(Constants.Items)
        .add(item)
        .addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener {
            onFailure()
        }
}
