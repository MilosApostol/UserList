package com.example.list.predefinedlook

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.list.data.items.FirebaseViewModel
import com.example.list.data.list.ListEntity
import com.example.list.data.list.ListViewModel
import com.example.list.navigation.Screen
import com.example.list.navigation.Screens

@Composable
fun Lists(
    lists: List<ListEntity>,
    listViewModel: ListViewModel,
    navController: NavController,
    firebaseViewModel: FirebaseViewModel,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    )
    {
        items(
            lists
        ) { list ->
            ListItems(
                list = list,
                onDeleteClick = {
                    listViewModel.removeList(list)
                    firebaseViewModel.removeAll(list.id.toString())
                },
                onRenameClick = {
                    val id = list.id
                    navController.navigate(Screen.DrawerScreen.Add.route + "/$id")
                },
                onTextClick = {
                    val id = list.id
                    navController.navigate(Screens.ItemsScreen.name + "/$id")
                }

            )
        }
    }
}