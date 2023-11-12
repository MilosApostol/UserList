package com.example.list.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.list.Screens
import com.example.list.data.ListDao
import com.example.list.data.ListViewModel
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.collectAsState

@Inject
lateinit var listDao: ListDao
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavHostController, listViewModel: ListViewModel = hiltViewModel()) {
    val allListsState by rememberUpdatedState(newValue = viewModel.getAllLists())

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Screen 2 Title") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
        Surface(color = Color(0xFFffe9d6.toInt()), modifier = Modifier.weight(1f)) {
            LazyVerticalGrid( columns = GridCells.Adaptive(minSize = 128.dp)
                 ) {
                items(allListsState) { list ->
                    // Display each item in the list in a separate column
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                // Handle item click as needed
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = list.name) // Assuming 'name' is a property in ListEntity
                            // Add other details if needed
                        }
                    }
                }
            }
                Button(onClick = { navController.navigate(Screens.LogInScreen.name) }) {
                    Text(text = "Screen 2 Title")

                }
                Text(text = "seksi")
            }
        }
    }
}