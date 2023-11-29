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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.data.ListViewModel
import com.example.list.navigation.Screen
import com.example.list.navigation.screensInDrawer
import com.example.list.predefinedlook.AppBarView
import com.example.list.predefinedlook.DrawerItem
import com.example.list.predefinedlook.ListItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ListScreen(
    navController: NavController = rememberNavController(),
    listViewModel: ListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lists = listViewModel.getAllLists.collectAsState(initial = listOf())
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val currentRoute = navController.currentDestination?.route
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
                        }
                    }
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
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // swipe to delete
            items(
                lists.value
            ) { list ->
                ListItems(
                    list = list,
                    onDeleteClick = { listViewModel.removeList(list) },
                    onRenameClick = {
                        val id = list.id
                        navController.navigate(Screen.DrawerScreen.Add.route + "/$id")
                    },
                    onTextClick = { navController.navigate(Screen.DrawerScreen.ItemsScreen.route) }

                )
            }

        }
    }
}
