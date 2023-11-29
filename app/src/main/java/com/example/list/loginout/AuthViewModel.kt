package com.example.list.loginout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.data.User
import com.example.list.userdata.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userSessionManager: UserSessionManager
) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            userSessionManager.clearSession()
        }
    }
}
