package com.example.list.data.userdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.sessionmanager.UserSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userSessionManager: UserSessionManager,
) : ViewModel() {

    lateinit var getAllUsers: Flow<List<User>>

    init {
        viewModelScope.launch {
            getAllUsers = userRepository.getAllUsers()
        }
    }

    private fun getUserByUsername(userName: String): User {
        return userRepository.getUserByUsername(userName)
    }

    suspend fun insertUser(user: User): Boolean {
        return withContext(Dispatchers.IO) {
            val existingUser = userRepository.getUserName(user.name)
            if (existingUser == null) {
                userRepository.insertUser(user)
                userSessionManager.setUserLoggedIn(true)
                userSessionManager.setUser(getUserByUsername(user.name))
                true
            } else {
                false
            }
        }
    }

    fun setUserById(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedUser = userRepository.getUserById(userId)
            userSessionManager.setUser(savedUser)
        }
    }

    suspend fun login(name: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val user = userRepository.getUserName(name)
            if (user != null) {
                userSessionManager.setUserLoggedIn(true)
                userSessionManager.setUser(user = user)
            }
            user?.let {
                userSessionManager.setUserLoggedIn(true)
                return@withContext it.password == password
            }
            return@withContext false
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            userSessionManager.logout()
        }
    }
}


