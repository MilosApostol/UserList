package com.example.list.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.Screen
import com.example.list.data.ListEntity
import com.example.list.data.ListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddList(id: Int, navController: NavController, listViewModel: ListViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var listName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    if (id != 0) { // editting/adding the details to the add screen
        val list = listViewModel.getListById(id).collectAsState(initial = ListEntity(0, 0, ""))
        listViewModel.listNameState = list.value.listName
    } else {
        listViewModel.listNameState = ""
    }

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
                ListNameTextField(
                    label = "ListName",
                    value = listViewModel.listNameState,
                    onValueChanged = { listViewModel.onListNameChanged(it) }
                )
                Button(
                    onClick = {
                        if (listViewModel.listNameState.isNotEmpty()) {
                            if (id != 0) {
                                listViewModel.updateList(
                                    ListEntity(
                                        id = id,
                                        listName = listViewModel.listNameState
                                    )

                                )
                                navController.navigate(Screen.DrawerScreen.List.route)

                                isLoading = true
                            } else {
                                listViewModel.addList(
                                    ListEntity(
                                        listName = listViewModel.listNameState
                                    )
                                )
                                navController.navigate(Screen.DrawerScreen.List.route)
                                isLoading = true
                            }
                        } else {
                            Toast.makeText(context, "addListName", Toast.LENGTH_LONG).show()
                        }
                    },
                    enabled = !isLoading
                ) {
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

@Preview
@Composable
fun preView() {
    val navController = rememberNavController()
    AddList(navController = navController, id = 0)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListNameTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChanged,
        label = {
            Text(
                text = label, color = Color.Black
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}