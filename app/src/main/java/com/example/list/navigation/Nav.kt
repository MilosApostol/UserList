package com.example.list.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
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
        startDestination = Graph.AUTH // Start destination is the nested graph, not LogInScreen
    ) {
        navigation(
            startDestination = Screens.LogInScreen.name,
            route = Graph.AUTH
        ) {
            composable(Screens.LogInScreen.name) {
                LogInScreen(navController = navController)
            }
        }
        composable(Screens.RegisterScreen.name) {
            RegisterScreen(navController = navController)
        }
        composable(
            Screen.DrawerScreen.List.route
        ) {
            ListScreen(navController = navController)
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
        composable(
            Screens.ItemsScreen.name + "/{id}",
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
            Screens.AddItems.name + "/{id}",
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
