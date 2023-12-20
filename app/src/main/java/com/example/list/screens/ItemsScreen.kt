package com.example.list.screens

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.list.data.items.FirebaseViewModel
import com.example.list.data.list.ListEntity
import com.example.list.data.list.ListViewModel
import com.example.list.navigation.Screens
import com.example.list.predefinedlook.ItemsList


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(
    id: Int,
    navController: NavController = rememberNavController(),
    listViewModel: ListViewModel = hiltViewModel(),
    firebaseViewModel: FirebaseViewModel = hiltViewModel()
) {

    val itemsFlow = firebaseViewModel.getAllItems

    val items = itemsFlow.collectAsState(emptyList()).value
    val context = LocalContext.current
    //getting a parent LIST
    val list = listViewModel.getListById(id)
        .collectAsState(initial = ListEntity(0, 0, "")).value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = list.listName) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu")
                    }
                })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.AddItems.name + "/$id")
                       //   navController.navigate(Screens.AddItemsSearch.name)
                },
            ) {
                Icon(Icons.Filled.Add, "Add List")
            }
        },
    )
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(
                items,
                key = { item -> item.itemId } // it has to have a key, swipe wouldn't work without it

            ) { item ->
                val dismissState = rememberDismissState()

                if (dismissState.isDismissed(direction = DismissDirection.EndToStart)) {
                    Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
                    firebaseViewModel.removeItem(itemId = item.itemId)
                }

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(
                        DismissDirection.EndToStart
                    ),
                    background = {
                        val color by animateColorAsState(
                            targetValue =
                            if (dismissState.dismissDirection ==
                                DismissDirection.EndToStart
                            ) Color.Red //red color
                            else Color.Transparent,
                            label = ""
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    dismissThresholds = { FractionalThreshold(0.5f) },
                    dismissContent = {
                        if (item.itemCreatorId == id.toString()) {
                            ItemsList(item = item)
                        } else {
                            Box(modifier = Modifier
                                .width(0.dp)
                                .height(0.dp))

                        }
                    })
            }
        }
    }
}

@Preview
@Composable
fun itemsScreen() {
    ItemsScreen(id = 0)
}
