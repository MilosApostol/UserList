package com.example.list.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.list.R
import com.example.list.navigation.Screen
import com.example.list.userdata.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences(stringResource(R.string.app_prefs), Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("userID", 0)
    var checkIn = sharedPreferences.getBoolean("check", false)
    if (userId != 0 && checkIn) {
        val setUser = userViewModel.setUserById(userId)
        navController.navigate(Screen.DrawerScreen.List.route)

    } else {
        var check by remember { mutableStateOf(false) }
        var name by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
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
                            value = name,
                            onValueChange = { name = it },
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
                        Checkbox(
                            checked = check, onCheckedChange = {
                                check = it
                                val editor = sharedPreferences.edit().apply {
                                    putBoolean("checked", check)
                                    apply()
                                }
                            }
                        )
                    }
                    Button(onClick = {
                        scope.launch {
                            val user = userViewModel.login(name, password)
                            if (user) {
                                navController.navigate(Screen.DrawerScreen.List.route)
                                Toast.makeText(context, "Welcome back $name", Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                Toast.makeText(context, "kme", Toast.LENGTH_LONG).show()
                            }
                        }
                    }) {
                        Text("Log In")
                    }

                    Button(onClick = { navController.navigate(Screen.DrawerScreen.Register.route) }) {
                        Text(text = "Register")
                    }
                }
            }
        }
    }
}