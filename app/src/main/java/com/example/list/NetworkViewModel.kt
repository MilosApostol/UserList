package com.example.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class NetworkViewModel : ViewModel() {
    var isNetworkAvailable: Boolean by mutableStateOf(true)

}
