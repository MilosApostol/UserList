package com.example.list.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.ListEvent
import com.example.list.ListItems
import com.example.list.Screens
import com.example.list.data.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddList(navController: NavController, listViewModel: ListViewModel = hiltViewModel()) {
    val listState = listViewModel.lists.value
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    //ui

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "AddListScreen") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                }
            }
        )
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("List name") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(listState.lists) { list ->
                        ListItems(
                            list = list,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screens.LogInScreen.name +
                                                "?noteId=${list}&"
                                    )
                                },
                            onDeleteClick = {
                                listViewModel.onEvent(ListEvent.DeleteList(list))
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

        /*
                Button(
                    onClick = {
                        isLoading = true
                        val newList = ListEntity(0, listName = text)
                        listViewModel.onEvent(AddList)
                        listViewModel.(newList)// or just (text)
                        navController.navigate(Screens.ListsScreen.name)
                    },
                    enabled = !isLoading
                )
                 {

                    if (isLoading) {
                        CircularProgressIndicator()
                    } else {
                        Text("Add List")
                    }
                }
            }
        }
    }
}

                 */
        @Preview
        @Composable
        fun preView() {
            val navController = rememberNavController()
            AddList(navController = navController)
        }
