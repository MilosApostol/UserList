package com.example.list.navigation

import androidx.annotation.DrawableRes
import com.example.list.R


sealed class Screen(
    val title: String,
    val route: String
) {

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(title = dTitle, route = dRoute) {
        data object List : DrawerScreen("ListScreen", "list_route", R.drawable.ic_list)
        data object UserListsScreen : DrawerScreen("UserListsScreen", "userList_route", R.drawable.ic_launcher_foreground)
        data object Add : DrawerScreen("AddScreen", "add_route", R.drawable.ic_add)
        data object ItemsScreen : DrawerScreen("ItemsScreen", "items_route", com.google.firebase.storage.R.drawable.common_full_open_on_phone)
        data object AddItems : DrawerScreen("AddItems", "add_items", R.drawable.ic_add)
        data object Register :
            DrawerScreen(
                "RegisterScreen",
                "register_route",
                R.drawable.baseline_app_registration_24
            )

        data object LogIn : DrawerScreen("LogInScreen", "login_route", R.drawable.baseline_login_24)
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.List,
    Screen.DrawerScreen.Add,
    Screen.DrawerScreen.LogIn,
    Screen.DrawerScreen.Register,
    Screen.DrawerScreen.UserListsScreen,
    Screen.DrawerScreen.ItemsScreen,
    Screen.DrawerScreen.ItemsScreen,
    Screen.DrawerScreen.AddItems
)



