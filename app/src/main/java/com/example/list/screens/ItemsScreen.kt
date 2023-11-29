package com.example.list.screens

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.data.ListEntity
import com.example.list.itemlists.ListsItemViewModel
import com.example.list.items.FirebaseViewModel
import com.example.list.navigation.Screen
import com.example.list.navigation.screensInDrawer
import com.example.list.predefinedlook.AppBarView
import com.example.list.predefinedlook.DrawerItem
import com.example.list.predefinedlook.ListItems
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemsScreen(
    navController: NavController = rememberNavController(),
    firebaseViewModel: FirebaseViewModel = hiltViewModel(),
    listsItemViewModel: ListsItemViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBarView(
                title = "ListScreen",
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
                onClick = {//todo
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
            val lists = ListEntity(id = 0, listName = " empty")
            // swipe to delete
            items(
                lists.id, //lists.value
                key = { lists -> lists }) { list -> // it has to have a key, it wouldnt work without it
                val dismissState = rememberDismissState(confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                        firebaseViewModel.deleteItem(itemsId = "")
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
