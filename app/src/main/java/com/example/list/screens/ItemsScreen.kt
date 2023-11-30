package com.example.list.screens

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.Composable
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

    fetchItems { updatedItems ->
        items = updatedItems
    }
    for (document in items){
        Toast.makeText(context, "$document", Toast.LENGTH_SHORT).show()
    }

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
            items(
                items.size,  //lists.value
                key = { lists -> lists }) { list -> // it has to have a key, it wouldnt work without it
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
                        /*ListItems(
                            list = list,
                        ) {
                            val id = list.id
                            navController.navigate(Screen.DrawerScreen.Add.route + "/$id")
                            Toast.makeText(context, "Proba", Toast.LENGTH_LONG).show()

                        }
                    }

                         */
                    }
                )
            }
        }
    }
}

fun fetchItems(onSuccess: (List<Items>) -> Unit) {
    val db = Firebase.firestore
    db.collection(Constants.Items)
        .get()
        .addOnSuccessListener { result ->
            val items = result.documents.map { document ->
                val itemName = document.getString("listName") ?: ""
                val listCreatorId = document.getString("listCreatorId") ?: 0
                Items(itemName = itemName, itemCreatorId = listCreatorId.hashCode())
            }
            onSuccess(items)
        }
        .addOnFailureListener { exception ->

        }
}