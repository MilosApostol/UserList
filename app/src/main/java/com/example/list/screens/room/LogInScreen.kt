package com.example.list.screens.room

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.list.R
import com.example.list.navigation.Screen
import com.example.list.data.userdata.UserViewModel
import com.example.list.navigation.Screens
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val auth = Firebase.auth

    val sharedPreferences =
        context.getSharedPreferences(stringResource(R.string.app_prefs), Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("userID", 0)
    var checkIn = sharedPreferences.getBoolean("check", false)


    /*
    val currentUser = auth.currentUser
    if (currentUser != null) {
        reload()
    }



     */
    
    if (userId != 0) {
        val setUser = userViewModel.setUserById(userId)
        navController.navigate(Screen.DrawerScreen.List.route)
    } else {
        var check by remember { mutableStateOf(false) }
        var name by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()


        Column(modifier = Modifier.fillMaxWidth()) {
            TopAppBar(
                title = { Text("LogInScreen") },
            )
            Scaffold(
                modifier = Modifier.fillMaxSize(),
            ) { padding ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = name,
                            onValueChange = { name = it.trim() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp),
                            label = { Text("Email") },
                        )

                        TextField(modifier = Modifier.fillMaxWidth(),
                            value = password,
                            onValueChange = {
                                password = it.trim()
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
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Checkbox(checked = check, onCheckedChange = {
                                check = it
                                val editor = sharedPreferences.edit().apply {
                                    putBoolean("checked", check)
                                    apply()
                                }
                            })
                            Text(text = "Stay Logged")

                        }
                        Button(
                            onClick = {
                                scope.launch {
                                    auth.signInWithEmailAndPassword(name, password)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                val user = auth.currentUser
                                                navController.navigate(Screen.DrawerScreen.List.route) {
                                                    popUpTo("auth") {
                                                        inclusive = true
                                                    }
                                                    Toast.makeText(
                                                        context,
                                                        "Welcome back $name",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Invalid credentials",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                            /*
                                            val user = userViewModel.login(name, password)
                                            if (user) {
                                                navController.navigate(Screen.DrawerScreen.List.route) {
                                                    popUpTo("auth") {
                                                        inclusive = true
                                                    }
                                                    Toast.makeText(
                                                        context, "Welcome back $name", Toast.LENGTH_LONG
                                                    ).show()
                                                }

                                             */
                                        }
                                    Toast.makeText(
                                        context, "Invalid credentials", Toast.LENGTH_LONG
                                    ).show()
                                }
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            Text("Log In")
                        }

                        Button(
                            onClick = { navController.navigate(Screens.RegisterScreen.name) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            Text(text = "Register")
                        }
                    }
                }
            }
        }
    }
}

    /*
    @Composable
    fun FirebaseLogin(auth: FirebaseAuth, name: String, password: String) {
        auth.signInWithEmailAndPassword(name, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
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
    @Preview
    @Composable
    fun log() {
        LogInScreen()
    }
