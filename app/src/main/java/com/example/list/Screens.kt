package com.example.list

import androidx.annotation.DrawableRes


sealed class Screen(
    val title: String,
    val route: String
) {

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        Screen(title = dTitle, route = dRoute) {
        object List : DrawerScreen("ListScreen", "list_route", R.drawable.ic_list)
        object Add : DrawerScreen("AddScreen", "add_route", R.drawable.ic_add)
        object Register :
            DrawerScreen(
                "RegisterScreen",
                "register_route",
                R.drawable.baseline_app_registration_24
            )

        object LogIn : DrawerScreen("LogInScreen", "login_route", R.drawable.baseline_login_24)
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.List,
    Screen.DrawerScreen.Add,
    Screen.DrawerScreen.LogIn,
    Screen.DrawerScreen.Register
)

