package com.example.list

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.list.screens.AddList
import com.example.list.screens.ListScreen
import com.example.list.screens.LogInScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
<<<<<<< HEAD
        startDestination = Screens.AddListScreen.name
=======
        startDestination = Screens.ListsScreen.name
>>>>>>> origin/to-be-renewed
    ) {
        composable(Screens.ListsScreen.name) {
            ListScreen(navController = navController)
        }
        composable(Screens.LogInScreen.name) {
            LogInScreen(navController = navController)
        }
        composable(Screens.AddListScreen.name){
            AddList(navController = navController)
        }
    }
}
