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
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.DrawerScreen.List.route
    ) {
        composable(
            Screen.DrawerScreen.List.route
        ) {
            ListScreen(navController = navController)
        }
        composable(Screen.DrawerScreen.LogIn.route) {
            LogInScreen(navController = navController)
        }
        composable(Screen.DrawerScreen.Add.route) {
            AddList(navController = navController)
        }
        composable(Screen.DrawerScreen.Register.route) {
            AddList(navController = navController)
        }
    }
}
