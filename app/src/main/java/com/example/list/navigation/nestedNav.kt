package com.example.list.navigation

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.list.screens.LogInScreen


object Graph {
    const val AUTH = "auth_graph"
    const val Add = "add"
    const val Root = "root"
}
@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.AUTH
    ) {

    }
}

@Composable
fun NestedNav(
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = Screens.LogInScreen.name
    )
    {
        navigation(
            startDestination = Screens.LogInScreen.name,
            route = "auth"
        ) {
            composable(Screens.LogInScreen.name) {
                Button(onClick = {
                    navController.navigate(Screens.ListScreen.name) {
                        popUpTo("auth") {
                            inclusive =
                                true
                            // as soon as you log in everyting is gone, nothing stays in the backstack
                        }
                    }
                }) {

                }

            }
        }

    }
}