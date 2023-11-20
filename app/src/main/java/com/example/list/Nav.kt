package com.example.list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.list.data.ListViewModel
import com.example.list.screens.AddList
import com.example.list.screens.ListScreen
import com.example.list.screens.LogInScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screens.ListsScreen.name
    ) {
        composable(Screens.ListsScreen.name) {
            ListScreen(navController = navController)
        }
        composable(Screens.LogInScreen.name) {
            LogInScreen(navController = navController)
        }
        composable(Screens.AddListScreen.name) {
            AddList(navController = navController)
        }
    }
}
