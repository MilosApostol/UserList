package com.example.list.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.R
import com.example.list.data.items.FirebaseViewModel
import com.example.list.data.list.ListViewModel
import com.example.list.data.userdata.UserViewModel
import com.example.list.data.userlists.UserListsViewModel
import com.example.list.navigation.Screen
import com.example.list.navigation.Screens
import com.example.list.navigation.screensInDrawer
import com.example.list.predefinedlook.AppBarView
import com.example.list.predefinedlook.DrawerItem
import com.example.list.predefinedlook.ListItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ListScreen(
    navController: NavController = rememberNavController(),
    listViewModel: ListViewModel = hiltViewModel(),
    userListsViewModel: UserListsViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    firebaseViewModel: FirebaseViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val currentRoute = navController.currentDestination?.route
    val listById = userListsViewModel.getListsByUserId().collectAsState(initial = listOf()).value
    var id = 0
    val userId = userListsViewModel.getUser()?.userId
    val sharedPreferences =
        context.getSharedPreferences(stringResource(R.string.app_prefs), Context.MODE_PRIVATE)
    var checkIn = sharedPreferences.getBoolean("checked", false)

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
                        listViewModel.deleteAllLists()
                    }
                },
                onLogoutClicked = {
                    scope.launch {
                        userViewModel.logout()
                        val editor = sharedPreferences.edit().apply {
                            if (checkIn) {
                                checkIn = false
                                putInt("userID", 0)
                            }
                            apply()
                        }
                        navController.navigate(Screens.LogInScreen.name)
                    }
                })

            Toast.makeText(context, "$checkIn", Toast.LENGTH_LONG).show()
            if (checkIn) {
                val editor = sharedPreferences.edit().apply {
                    if (userId != null) {
                        putInt("userID", userId)
                    }
                    apply()
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
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
                        if (item.dRoute == Screen.DrawerScreen.Add.route) {
                            navController.navigate(Screen.DrawerScreen.Add.route + "/$id")
                        } else {
                            navController.navigate(item.dRoute)
                        }
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
            items(
                listById
            ) { list ->
                ListItems(
                    list = list,
                    onDeleteClick = {
                        listViewModel.removeList(list)
                        firebaseViewModel.removeAll(list.id.toString())
                    },
                    onRenameClick = {
                        val id = list.id
                        navController.navigate(Screen.DrawerScreen.Add.route + "/$id")
                    },
                    onTextClick = {
                        val id = list.id
                        navController.navigate(Screens.ItemsScreen.name + "/$id")
                    }

                )
            }

        }
    }
}
