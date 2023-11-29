package com.example.list.navigation

import androidx.compose.material3.Button
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
/*
class nestedNav {
    val navController = rememberNavController()
    NavHost(
    navController = navController,
    startDestination = Screens.LogInScreen.name
    ) {
        navigation(
            startDestination = "login",
            route = "auth"
        ) {
            composable("login") {
                val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)
                Button(onClick = {
                    navController.navigate("proba"){
                        popUpTo("auth"){
                            inclusive = true // as soon as you log in everyting is gone, nothing stays in the backstack
                        }
                    }
                }){

                }

            }

            composable("register") {

                val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)

            }
            composable("forgot") {
                val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)


            }
        }
        navigation(startDestination = Screens.ListsScreen.name, route = "proba"){
            composable()

        } }

}

 */
