package com.example.list.screens

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.AppBarView
import com.example.list.DrawerItem
import com.example.list.ListItems
import com.example.list.Screen
import com.example.list.data.ListViewModel
import com.example.list.screensInDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
<<<<<<< HEAD
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
=======
fun ListScreen(
    navController: NavController = rememberNavController(),
    listViewModel: ListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lists = listViewModel.getAllLists.collectAsState(initial = listOf())
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val currentRoute = navController.currentDestination?.route
    val currentScreen = remember { listViewModel.currentScreen.value }
    val title = remember { mutableStateOf(currentScreen.title) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBarView(
                title = "ListScreen",
                onMenuNavClicked = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
                        }

                    }
<<<<<<< HEAD
                 */
=======
                },
                onDeleteNavClicked = {
                    scope.launch {
                        //Todo not working
                        listViewModel.deleteAllLists()
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val id = 0
                    navController.navigate(Screen.DrawerScreen.Add.route + "/$id")
                },
            ) {
                Icon(Icons.Filled.Add, "Add List")
            }//drawerItems
        }, drawerContent = {
            LazyColumn(Modifier.padding(16.dp)) {
                items(screensInDrawer) { item ->
                    DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        navController.navigate(item.dRoute)
                        title.value = item.dTitle
                    }
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
                }
            }
        }
<<<<<<< HEAD
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
=======
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // swipe to delete
            items(
                lists.value,
                key = { lists -> lists.id }) { list -> // it has to have a key, it wouldnt work without it
                val dismissState = rememberDismissState(confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                        listViewModel.removeList(list = list)
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
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
                        }
                    },
                    directions = setOf(
                        DismissDirection.EndToStart,
                    ),
                    dismissThresholds = { FractionalThreshold(0.5f) },
                    dismissContent = {
                        ListItems(
                            list = list
                        ) {
                            val id = list.id
                            navController.navigate(Screen.DrawerScreen.Add.route + "/$id")
                            Toast.makeText(context, "Proba", Toast.LENGTH_LONG).show()

                        }
                    }
                )
            }
        }
    }
}
