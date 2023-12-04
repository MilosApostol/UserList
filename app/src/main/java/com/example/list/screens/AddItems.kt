package com.example.list.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.Constants
import com.example.list.data.items.Items
import com.example.list.navigation.Screen
import com.example.list.navigation.Screens
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItems(
    id: Int,
    navController: NavController = rememberNavController(),
) {
    var itemName by remember { mutableStateOf("") }
    val context = LocalContext.current

    TopAppBar(title = { "AddItems" }, modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu")
            }
        })
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
        Button(modifier = Modifier.padding(16.dp),
            onClick = {
                if (itemName.isNotEmpty()) {
                    saveData(itemName, id, navController)
                } else {
                    Toast.makeText(context, "add item name", Toast.LENGTH_LONG).show()
                }
            }) {
            Text(text = "add item")
        }
    }
}

fun saveData(itemName: String, id: Int, navController: NavController) {

    val db = Firebase.database
    val ref = db.getReference(Constants.Items)
    val itemsRef = ref.child("items")
    val newItem = Items(
        itemName = itemName,
        itemId = UUID.randomUUID().toString(),
        itemCreatorId = id.toString()
    )
    ref.child(newItem.itemId).setValue(newItem).addOnCompleteListener {
        navController.navigate(Screens.ItemsScreen.name + "/$id"){
            popUpTo(Screens.AddItems.name + "$id"){
                inclusive = true
            }
        }


    }.addOnFailureListener {

    }

}
