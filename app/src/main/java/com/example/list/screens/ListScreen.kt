package com.example.list.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.list.Screens
import com.example.list.data.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavHostController) {
    val listViewModel: ListViewModel = hiltViewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Screen 2 Title") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
        Surface(color = Color(0xFFffe9d6.toInt()), modifier = Modifier.weight(1f)) {
            LazyColumn(
            ) {
                /*
               // items(listViewModel.lists) { list ->
                    // Display each item in the list in a separate column
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                // Handle item click as needed
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = list.uid.toString())
                            Text(text = list.listName.orEmpty()) // Assuming 'listName' is a property in ListEntity
                        }

                    }
                 */
                }
            }


            Button(onClick = { navController.navigate(Screens.LogInScreen.name) }) {
                Text(text = "Screen 2 Title")
            }
        }
    }

/*
    @Composable
    fun ListS(navController: NavController, listViewModel: ListViewModel = hiltViewModel()) {
        Scaffold(
            floatingActionButton =
            {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screens.AddListScreen.name)
                    },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Filled.Add, "Add List")
                }
            })
        { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = "Your lists", modifier = Modifier.padding(paddingValues))
            }
            LazyColumn(modifier = Modifier.fillMaxSize())
            {
                items(listViewModel.lists) { list ->
                    ListItems(list = list, modifier = Modifier.fillMaxSize()
                        .clickable {},
                        onDeleteClick = {
                            /*
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo"
                                )
                                if(result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NotesEvent.RestoreNote)
                                }
                            }
                        }

                             */
                        })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

 */