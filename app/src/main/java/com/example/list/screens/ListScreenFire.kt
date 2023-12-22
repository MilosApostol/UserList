package com.example.list.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.Constants
import com.example.list.R
import com.example.list.data.firebase.items.FirebaseViewModel
import com.example.list.data.firebase.items.Items
import com.example.list.data.list.ListEntity
import com.example.list.data.list.ListViewModel
import com.example.list.data.userdata.User
import com.example.list.data.userdata.UserViewModel
import com.example.list.data.userlists.UserListsViewModel
import com.example.list.loginstate.LoginViewModel
import com.example.list.navigation.Screen
import com.example.list.navigation.Screens
import com.example.list.navigation.screensInDrawer
import com.example.list.predefinedlook.AppBarView
import com.example.list.predefinedlook.DrawerItem
import com.example.list.predefinedlook.ItemsList
import com.example.list.predefinedlook.ListList
import com.example.list.predefinedlook.Lists
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ListScreenFire(
    navController: NavController = rememberNavController(),
    listViewModel: ListViewModel = hiltViewModel(),
    userListsViewModel: UserListsViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    firebaseViewModel: FirebaseViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val listFlow = listViewModel.getAllLists
    val list = listFlow.collectAsState(initial = emptyList()).value


    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    val currentRoute = navController.currentDestination?.route
    val sharedPreferences =
        context.getSharedPreferences(stringResource(R.string.app_prefs), Context.MODE_PRIVATE)

    Toast.makeText(context, "${Firebase.auth.currentUser}", Toast.LENGTH_LONG).show()

    //network
    val networkState by listViewModel.networkState.collectAsState()
    val db = Firebase.database
    val user = Firebase.auth.currentUser

    // val newUser = user.displayName?.let { User(userId = user.uid.toInt(), name = it) }
    // db.getReference("users").child(user.uid).setValue(newUser)

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
                        //todo
                    }
                },
                onLogoutClicked = {
                    Firebase.auth.signOut()
                    /*
                    scope.launch {
                        try {
                            userViewModel.logout()
                            scope.launch {
                                Firebase.auth.signOut()
                            }
                            loginViewModel.clearCredentials()
                            loginViewModel.authStateListener?.let {
                                Firebase.auth.removeAuthStateListener(
                                    it
                                )
                            }
                            navController.navigate(Screens.LogInScreen.name)
                        } catch (e: Exception) {

                        }

                     */
                })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddListFire.name)
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
                            navController.navigate(Screens.AddListFire.name)
                        } else {
                            navController.navigate(item.dRoute)
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Lists(list, listViewModel, navController, firebaseViewModel, paddingValues)
//TODO WHAT IF NETWORK IS OFFFFF

    }
}

