package com.example.list.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.list.screens.AddItems
import com.example.list.screens.AddList
import com.example.list.screens.ItemsScreen
import com.example.list.screens.ListScreen
import com.example.list.screens.LogInScreen
import com.example.list.screens.RegisterScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.DrawerScreen.LogIn.route
    ) {
        composable(
            Screen.DrawerScreen.List.route
        ) {
            ListScreen(navController = navController)
        }
        composable(Screen.DrawerScreen.LogIn.route) {
            LogInScreen(navController = navController)
        }
        composable(
            Screen.DrawerScreen.Add.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
                defaultValue = 0
                nullable = false
            })
        ) { //taking the id
            val id = if (it.arguments != null) it.arguments!!.getInt("id") else 0
            AddList(id = id, navController = navController)
        }
        composable(Screen.DrawerScreen.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(
            Screen.DrawerScreen.ItemsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })
        ) {
            val id = if (it.arguments != null) it.arguments!!.getInt("id") else 0
            ItemsScreen(id = id, navController = navController)
        }

        composable(
            Screen.DrawerScreen.AddItems.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                })
        ) {
            val id = if (it.arguments != null) it.arguments!!.getInt("id") else 0
            AddItems(id = id, navController = navController)
        }
    }
}
