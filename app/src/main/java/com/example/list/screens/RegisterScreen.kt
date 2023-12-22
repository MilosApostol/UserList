package com.example.list.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.list.R
import com.example.list.data.userdata.User
import com.example.list.data.userdata.UserViewModel
import com.example.list.navigation.Screen
import com.example.list.navigation.Screens
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var check by remember { mutableStateOf(false) }
    val sharedPreferences = context.getSharedPreferences(
        stringResource(R.string.app_prefs), Context.MODE_PRIVATE
    )
    val auth = Firebase.auth




    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(title = { Text("RegisterScreen") }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu")
            }
        })
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { padding ->
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
                    TextField(modifier = Modifier.fillMaxWidth(),
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = { Text("Password") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        trailingIcon = {
                            IconButton(
                                onClick = { passwordVisible = !passwordVisible },
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.VisibilityOff
                                    else Icons.Default.Visibility,
                                    contentDescription = "Toggle password visibility"
                                )
                            }
                        })
                    Checkbox(checked = check, onCheckedChange = {
                        check = it
                        val editor = sharedPreferences.edit().apply {
                            putBoolean("stayLoggedIn", check)
                            apply()
                        }
                    })
                    Button(onClick = {
                        scope.launch {
                            auth.createUserWithEmailAndPassword(
                                name.trim(),
                                password.trim()
                            )
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(context, "made", Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(context, "failed", Toast.LENGTH_LONG).show()

                                    }
                                }
                            /*
                            val user = User(name = name, password = password)
                            val success = userViewModel.insertUser(user)
                            if (success) {
                                navController.navigate(Screen.DrawerScreen.List.route) {
                                    popUpTo("auth") {
                                        inclusive = true
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "User name is already taken, pick another one",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                             */
                        }
                    }) {
                        Text("Register")
                    }
                }
            }
        }
    }
}
/*
@Composable
fun RegisterUser(auth, name, password){
    auth.createUserWithEmailAndPassword(name, password)
        .addOnCompleteListener(thi  s) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                    baseContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
                updateUI(null)
            }
        }
}

 */

