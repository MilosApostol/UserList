package com.example.list.screens

<<<<<<< HEAD
import androidx.compose.foundation.clickable
=======
import android.widget.Toast
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
<<<<<<< HEAD
import androidx.compose.foundation.layout.height
=======
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
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
<<<<<<< HEAD
=======
import androidx.compose.runtime.collectAsState
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
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
<<<<<<< HEAD
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
=======
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
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524

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
<<<<<<< HEAD
                        isLoading = true
                        val newList = ListEntity(0, listName = text)
                        listViewModel.onEvent(AddList)
                        listViewModel.(newList)// or just (text)
                        navController.navigate(Screens.ListsScreen.name)
=======
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
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
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

<<<<<<< HEAD
                 */
        @Preview
        @Composable
        fun preView() {
            val navController = rememberNavController()
            AddList(navController = navController)
        }
=======
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
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
