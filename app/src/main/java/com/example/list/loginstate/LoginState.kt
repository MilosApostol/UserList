package com.example.list.loginstate

data class LoginState(
    val email: String = "",
    val password: String = "",
    val stayLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)