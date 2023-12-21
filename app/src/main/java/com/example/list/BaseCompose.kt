package com.example.list

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
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
}