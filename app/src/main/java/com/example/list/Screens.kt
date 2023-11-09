package com.example.list

enum class Screens {
    LogInScreen,
    ListsScreen,
    TrashScreen,
    SettingsScreen,
    ApperanceScreen,
    RemoveAdsScreen
}
/*
sealed class NavigationItem(val route: String){
    object List : NavigationItem(Screens.ListsScreen.name)
    object LogIn : NavigationItem(Screens.LogInScreen.name)
    object Trash : NavigationItem(Screens.TrashScreen.name)
    object Settings : NavigationItem(Screens.SettingsScreen.name)
    object Apperance : NavigationItem(Screens.ApperanceScreen.name)
    object RemoveAds : NavigationItem(Screens.RemoveAdsScreen.name)
}

 */