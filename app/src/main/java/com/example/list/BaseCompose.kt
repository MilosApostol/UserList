package com.example.list

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.list.internet.ConnectionState
import com.example.list.navigation.Screen
import com.example.list.ui.theme.ListTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


abstract class BaseCompose : ComponentActivity() {


    private var connectivityManager: ConnectivityManager? = null
    var connectionLost = false

    private val networkCallback: ConnectivityManager.NetworkCallback =
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                runOnUiThread {
                    if (connectionLost) {
                        onNetworkAvailable(true)
                    }
                }
            }

            override fun onLost(network: Network) {
                runOnUiThread {
                    onNetworkAvailable(false)
                    connectionLost = true
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ListTheme {
                    Surface(
                        color = MaterialTheme.colors.background
                    ) {

                    }
                }
            }
        }

    open fun onNetworkAvailable(available: Boolean) {}
    override fun onResume() {
        super.onResume()
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager?.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request =
                NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()
            connectivityManager?.registerNetworkCallback(request, networkCallback)
        }
    }

    override fun onPause() {
        super.onPause()
        connectivityManager?.unregisterNetworkCallback(networkCallback)
        connectivityManager = null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (connectivityManager != null) {
            connectivityManager?.unregisterNetworkCallback(networkCallback)
            connectivityManager = null
        }
    }

    fun attemptSilentLogin(sharedPreferences: SharedPreferences) {
        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)
        val stayLoggedIn = sharedPreferences.getBoolean("stayLoggedIn", false)

        if (email != null && password != null && stayLoggedIn) {
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Silent login successful
                    } else {
                        // Silent login failed, clear credentials and prompt for login
                        clearCredentials(sharedPreferences)
                    }
                }
        }
    }

    private fun clearCredentials(sharedPreferences: SharedPreferences) {
        sharedPreferences.edit()
            .remove("email")
            .remove("password")
            .putBoolean("stayLoggedIn", false)
            .apply()
    }

    fun handleLogin(
        email: String,
        password: String,
        navController: NavHostController,
        context: Context,
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    navController.navigate(Screen.DrawerScreen.List.route) {
                        popUpTo("auth") {
                            inclusive = true
                        }
                        Toast.makeText(
                            context,
                            "Welcome back $email",
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
            }
    }
}