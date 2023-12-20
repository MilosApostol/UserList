package com.example.list

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.list.internet.ConnectionState
import com.example.list.ui.theme.ListTheme


class BaseCompose : ComponentActivity() {

    private val networkViewModel:NetworkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check for network status
        val isNetworkAvailable = checkNetworkStatus()

        // Update the ViewModel
        networkViewModel.isNetworkAvailable = isNetworkAvailable

        // Set up Compose UI using setContent
        setContent {
            // Compose UI code here...
            ListTheme {
                ProvideNetworkState(networkViewModel.isNetworkAvailable) {
                    Surface(
                        color = MaterialTheme.colors.background
                    ) {

                    }
                }
            }
        }
    }

     fun checkNetworkStatus(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network =
            connectivityManager.activeNetwork
        Log.d("Network", "active network $network")
        network ?: return false  // return false if network is null
        val actNetwork = connectivityManager.getNetworkCapabilities(network)
            ?: return false // return false if Network Capabilities is null
        return when {
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> { // check if wifi is connected
                Log.d("Network", "wifi connected")
                true
            }

            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> { // check if mobile dats is connected
                Log.d("Network", "cellular network connected")
                true
            }

            else -> {
                Log.d("Network", "internet not connected")
                false
            }
        }
    }
}