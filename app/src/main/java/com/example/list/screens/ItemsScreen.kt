package com.example.list.screens

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.Constants
import com.example.list.data.itemlists.ListsItemViewModel
import com.example.list.data.items.Items
import com.example.list.data.list.ListEntity
import com.example.list.data.list.ListViewModel
import com.example.list.navigation.Screen
import com.example.list.predefinedlook.AppBarView
import com.example.list.predefinedlook.ItemsList
import com.example.list.predefinedlook.ListItems
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemsScreen(
    id: Int,
    navController: NavController = rememberNavController(),
    listsItemViewModel: ListsItemViewModel = hiltViewModel(),
    listViewModel: ListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var items by remember { mutableStateOf(emptyList<Items>()) }
    val list = listViewModel.getListById(id).collectAsState(initial = ListEntity(0, 0, ""))
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    //  LaunchedEffect(key1 = true) {
    fetchItems(
        onSuccess = { fetchedItems ->
            items = fetchedItems
        },
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBarView(
                title = list.value.listName,
                onMenuNavClicked = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.DrawerScreen.AddItems.route + "/$id")
                },
            ) {
                Icon(Icons.Filled.Add, "Add List")
            }
        },
    )
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(items) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = item.itemName)
                }

            }
        }
    }

}
fun fetchItems(onSuccess: (List<Items>, LocalContext) -> Unit) {
    val db = Firebase.firestore
    db.collection(Constants.Items)
        .get()
        .addOnSuccessListener { result ->
            if(result.isEmpty){
                return@addOnSuccessListener
            } else{
                val types: List<Items> = result.toObjects(Items::class.java)
            }
            val itemsList = mutableListOf<Items>()

            for (document in result) {
                // Convert each document to your Items data class
                val item = document.toObject(Items::class.java)
                itemsList.add(item)
            }
            onSuccess(itemsList)
        }
        .addOnFailureListener { exception ->
            Toast.makeText(, "Error getting data!!!", Toast.LENGTH_LONG).show()

        }
}

@Preview
@Composable
fun itemsScreen() {
    ItemsScreen(id = 0)
}

/*
paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(
                items,
                key = { item -> item.itemId } // it has to have a key, it wouldnt work without it

            ) { item ->
                val dismissState = rememberDismissState(confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                    }
                    true
                })
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            targetValue =
                            if (dismissState.dismissDirection ==
                                DismissDirection.EndToStart
                            ) Color.Red //red color
                            else Color.Transparent,
                            label = ""
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    directions = setOf(
                        DismissDirection.EndToStart,
                    ),
                    dismissThresholds = { FractionalThreshold(0.5f) },
                    dismissContent = {
                        ItemsList(
                            item = item
                        ) {
                            val id = item.itemId
                            navController.navigate(Screen.DrawerScreen.AddItems.route + "/$id")
                            Toast.makeText(context, "Proba", Toast.LENGTH_LONG).show()

                        }
                    })
 */


