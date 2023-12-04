package com.example.list.navigation

import androidx.annotation.DrawableRes
import com.example.list.R


enum class Screens{
    AddItems,
    ItemsScreen,
    LogInScreen,
    RegisterScreen,
    SplashScreen
}

sealed class Screen(
    val title: String,
    val route: String
) {

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(title = dTitle, route = dRoute) {
        data object List : DrawerScreen("ListScreen", "list_route", R.drawable.ic_list)
        data object Add : DrawerScreen("AddScreen", "add_route", R.drawable.ic_add)
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.List,
    Screen.DrawerScreen.Add,
)



