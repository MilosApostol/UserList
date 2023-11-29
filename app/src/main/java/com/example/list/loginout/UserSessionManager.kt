package com.example.list.loginout

import com.example.list.data.ListEntity
import com.example.list.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


class UserSessionManager{
    private var currentUser: User? = null
    private val _userLoggedIn = MutableStateFlow(false)

    fun setUser(user: User) {
        this.currentUser = user
    }

    fun getUserId(): Int? {
        return currentUser?.userId
    }

    fun getUser(): User? {
        return currentUser
    }

    fun setUserLoggedIn(loggedIn: Boolean) {
        _userLoggedIn.value = loggedIn
    }

    fun clearSession() {
        // Clear any session-related data
        setUserLoggedIn(false)
    }
}
