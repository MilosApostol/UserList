package com.example.list.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    val showError by remember { mutableStateOf(false) }

    //for snackbar
    val scaffoldstate = rememberScaffoldState()

    val context = LocalContext.current

    fun onLoginClick() {
        scope.launch {
            scaffoldstate.snackbarHostState.showSnackbar("loginClicked")
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = { Text("LogInScreen") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu")
                }
            }
        )
        Scaffold(
            scaffoldState = scaffoldstate,
            modifier = Modifier
                .fillMaxSize(),
        )
        { padding ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp),
                        label = { Text("Email") },
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = { Text("password") },
                    )

                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "LogIn")
                    }

                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Register")
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun LogShow() {
    LogInScreen(navController = rememberNavController())
}
