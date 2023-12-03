package com.example.list.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.Constants
import com.example.list.data.items.FirebaseViewModel
import com.example.list.data.items.Items
import com.example.list.data.list.ListEntity
import com.example.list.data.list.ListViewModel
import com.example.list.navigation.Screen
import com.example.list.predefinedlook.AppBarView
import com.example.list.predefinedlook.ItemsList
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemsScreen(
    id: Int,
    navController: NavController = rememberNavController(),
    listViewModel: ListViewModel = hiltViewModel(),
    firebaseViewModel: FirebaseViewModel = hiltViewModel()
) {

    val itemsFlow = firebaseViewModel.getAllItems

    val items = itemsFlow.collectAsState(emptyList()).value
    val context = LocalContext.current
    val list = listViewModel.getListById(id).collectAsState(initial = ListEntity(0, 0, ""))
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val database = Firebase.database

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
                items
            ) { item ->
                ItemsList(item)
            }

        }
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