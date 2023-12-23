package com.example.list.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.list.Constants
import com.example.list.data.list.ListEntity
import com.example.list.data.list.ListViewModel
import com.example.list.navigation.Screen
import com.example.list.navigation.Screens
import com.example.list.screens.room.ListNameTextField
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddListFire(
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel(),
) {
    val db = Firebase.database
    val context = LocalContext.current
    var listName by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "AddListScreen") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                }
            }
        )
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                ListNameTextField(
                    label = "ListName",
                    value = listName,
                    onValueChanged = { listName = it }
                )

                Button(
                    onClick = {
                        if (listName.isNotEmpty()) {
                            val list =
                                Firebase.auth.currentUser?.uid?.let { ListEntity(listName = listName, id = UUID.randomUUID().toString(), listCreatorId = it) }
                            db.getReference(Constants.Lists)
                                .child(FirebaseAuth.getInstance().currentUser?.uid ?: "")
                                .push()
                                .setValue(list)
                            navController.navigate(Screens.ListScreenFire.name)
                            isLoading = true

                        } else {
                            Toast.makeText(context, "addListName", Toast.LENGTH_LONG).show()
                        }
                    },
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator()
                    } else {
                        Text("Add List")
                    }
                }
            }
        }
    }
}