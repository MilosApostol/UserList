package com.example.list.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.list.screens.AddItems
import com.example.list.screens.room.AddList
import com.example.list.screens.AddListFire
import com.example.list.screens.ItemsScreen
import com.example.list.screens.room.ListScreen
import com.example.list.screens.ListScreenFire
import com.example.list.screens.room.LogInScreen
import com.example.list.screens.LoginFireBase
import com.example.list.screens.RegisterScreen

object Graph {
    const val AUTH = "auth_graph"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.LoginFireBase.name,
    ) {
        composable(Screens.LoginFireBase.name) {
            LoginFireBase(navController = navController)
        }
        navigation(
            route = Graph.AUTH,
            startDestination = Screens.LogInScreen.name,
        ) {
            composable(Screens.LogInScreen.name) {
                LogInScreen(navController = navController)
            }
            composable(Screens.RegisterScreen.name) {
                RegisterScreen(navController = navController)
            }
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


        composable(Screens.AddListFire.name) {
           AddListFire(navController)
        }
        composable(Screens.ListScreenFire.name) {
            ListScreenFire(navController)
        }
    }
}

