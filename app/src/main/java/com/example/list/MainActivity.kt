package com.example.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.list.R.string.app_name
import com.example.list.ui.theme.ListTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    NavDrawer()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NavDrawer() {
        val navigationController = rememberNavController()
        val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text("DrawerTitle", modifier = Modifier.padding(16.dp))
                    Divider()
                    NavigationDrawerItem(label = { Text(text = "Item") },
                        selected = false,
                        onClick = {
                            navigationController.navigate(Screens.ListsScreen.name)
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        })
                }
            },
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.primary
                        ),
                        title = {
                            Text(
                                text = stringResource(id = app_name)
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu, contentDescription = null
                                )

                            }
                        },
                    )
                },
            ) { innerPadding ->
                Text(text = "text", modifier = Modifier.padding(innerPadding))
                NavGraph(navigationController)
                //     FloatingActionButton(onClick = {
                //     navigationController.navigate(Screens.AddListScreen.name)
                ///  }) {
                //       Icon(Icons.Filled.Add, "Add List")
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListTheme {
        NavDrawer()
    }
}
}/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar has slots for a title, navigation icon,
        // and actions. Also known as the action bar.
        TopAppBar(
            title = { Text("ForgotPassword") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu")
                }
            }
        )
        Surface(color = Color(0xFFffd7d7.toInt()), modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Geeks for geeks : Geeks learning from geeks")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registration(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar has slots for a title, navigation icon,
        // and actions. Also known as the action bar.
        TopAppBar(
            title = { Text("Registration") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu")
                }
            }
        )
        Surface(color = Color(0xFFffd7d7.toInt()), modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Geeks for geeks : Geeks learning from geeks")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3Component(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Screen 3 Title") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )
        Surface(color = Color(0xFFfffbd0.toInt()), modifier = Modifier.weight(1f)) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Address: A-143, 9th Floor, Sovereign Corporate Tower Sector-136, Noida, Uttar Pradesh - 201305")
                }
            )
        }
    }
}

 */
