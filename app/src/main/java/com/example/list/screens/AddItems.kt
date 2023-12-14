package com.example.list.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.Constants
import com.example.list.data.additems.AddCustomViewModel
import com.example.list.data.api.additemsapi.AddItemsViewModel
import com.example.list.data.items.Items
import com.example.list.navigation.Screens
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItems(
    id: Int,
    navController: NavController = rememberNavController(),
    addItemsViewModel: AddItemsViewModel = hiltViewModel(),
    addCustomViewModel: AddCustomViewModel = hiltViewModel()
) {

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = addItemsViewModel.addItem.collectAsState()
    val scope = rememberCoroutineScope()
    val allItems by addCustomViewModel.getAllItems.collectAsState(initial = listOf())
    val filteredItems = allItems.filter { it.title.contains(text, ignoreCase = true) }

    LaunchedEffect(Unit) {
        scope.launch {
            addItemsViewModel.getItems()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopAppBar(title = { Text(text = "AddItems") }, modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu")
                }
            })
        DockedSearchBar(
            modifier = Modifier
                .padding(top = 8.dp)
                .semantics { traversalIndex = -1f }
                .fillMaxWidth(),
            query = text,
            onQueryChange = {
                text = it
                addItemsViewModel.onSearchChange(text)
            },
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("Hinted search text") },
            leadingIcon = {
                if (!active) {
                    Icon(Icons.Default.Search, contentDescription = null)
                } else {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable { active = false })
                }
            },
            trailingIcon = {
                if (active) {
                    Icon(Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                        }
                    )
                }
            }
        ) {
            LazyColumn() {
                items(filteredItems) { items ->
                    Text(text = items.title)

                }
                /*
                LazyColumn() {
                    items(items.value) { item ->
                        SearchItems(itemsData = item, onClick = {
                            addItemsViewModel.addToSelectedItems(item)
                            active = false
                            saveData(item.title, id, navController)

                        })
                    }

                 */
            }
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
        navController.navigate(Screens.ItemsScreen.name + "/$id") {
            popUpTo(Screens.AddItems.name + "$id") {
                inclusive = true
            }
        }


    }.addOnFailureListener {

    }

}
