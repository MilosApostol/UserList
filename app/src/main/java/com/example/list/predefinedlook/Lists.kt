package com.example.list.predefinedlook

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.list.data.firebase.items.FirebaseViewModel
import com.example.list.data.list.ListEntity
import com.example.list.data.list.ListViewModel
import com.example.list.navigation.Screen
import com.example.list.navigation.Screens
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.core.Constants
import com.google.firebase.database.database
import java.util.UUID

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
                    val listKey = list.id
                    Firebase.database.getReference(com.example.list.Constants.Lists)
                        .child(FirebaseAuth.getInstance().currentUser?.uid ?: "")
                        .child(listKey)
                        .removeValue()

                },
                onRenameClick = {
                    updateList(list.listName, navController)
                    //  val id = list.id
                    // navController.navigate(Screen.DrawerScreen.Add.route + "/$id")
                },
                onTextClick = {
                    //    val id = list.id
                    //     navController.navigate(Screens.ItemsScreen.name + "/$id")
                }

            )
        }
    }
}

private fun updateList(username: String, navController: NavController) {
    val db = Firebase.database
    val ref = db.getReference(com.example.list.Constants.Lists)

    // Use a push key to create a new entry in the database
    val key = ref.push().key ?: return

    // Create a ListEntity
    val list = ListEntity(username)

    // Use setValue to update the child with the new list
    ref.child(FirebaseAuth.getInstance().currentUser?.uid ?: "")
        .child(key)
        .setValue(list)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navController.navigate(Screens.ListScreenFire.name)
            } else {
                // Handle failed update
            }
        }
}

private fun deleteList() {

}
