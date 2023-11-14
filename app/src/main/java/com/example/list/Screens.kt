package com.example.list

enum class Screens {
    LogInScreen,
    ListsScreen,
    TrashScreen,
    SettingsScreen,
    ApperanceScreen,
    RemoveAdsScreen,
    AddListScreen
}
/*
sealed class Screen(val route: String){
    object List : Screen(Screens.ListsScreen.name)
    object LogIn : Screen(Screens.LogInScreen.name)
    object Trash : Screen(Screens.TrashScreen.name)
    object Settings : Screen(Screens.SettingsScreen.name)
    object Apperance : Screen(Screens.ApperanceScreen.name)
    object RemoveAds : Screen(Screens.RemoveAdsScreen.name)
}

 */
