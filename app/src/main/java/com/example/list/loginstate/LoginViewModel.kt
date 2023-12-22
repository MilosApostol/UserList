package com.example.list.loginstate

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.list.navigation.Screen
import com.example.list.navigation.Screens
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    var authStateListener: FirebaseAuth.AuthStateListener? = null //alows me to remove it after signout


    private val _loginState = mutableStateOf(LoginState())
    val loginState = _loginState // holding whole data class for login

    // Access FirebaseAuth instance (replace with your actual initialization)
    private val auth = Firebase.auth

    init {
        // Attempt silent login on initialization
        attemptSilentLogin()
    }

    // Handle login button click
    fun onLoginClick(navController: NavHostController) {
        val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        _loginState.value =
            loginState.value.copy(isLoading = true, error = null) // Clear any previous errors
        handleLogin(
            loginState.value.email,
            loginState.value.password,
            loginState.value.stayLoggedIn,
            sharedPreferences = sharedPreferences,
            navController
        )
        navController.navigate(Screens.ListScreenFire.name)
    }

    // Handle login logic, updating state based on success/failure
    fun handleLogin(
        email: String,
        password: String,
        stayLoggedIn: Boolean,
        sharedPreferences: SharedPreferences,
        navController: NavHostController
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _loginState.value = loginState.value.copy(isLoading = false)
                if (task.isSuccessful) {
                    if (stayLoggedIn) {
                        sharedPreferences.edit()
                            .putString("email", email)
                            .putString(
                                "password",
                                password
                            )  // Consider hashing password before storing
                            .putBoolean("stayLoggedIn", true)
                            .apply()
                    }
                    navController.navigate(Screen.DrawerScreen.List.route)
                } else {
                    _loginState.value = loginState.value.copy(error = task.exception?.message)
                }
            }
    }


    // Attempt silent login on initialization
    fun attemptSilentLogin() {
        val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
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
                        clearCredentials()
                    }
                }
        }
    }

    fun clearCredentials() {
        val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .remove("email")
            .remove("password")
            .putBoolean("stayLoggedIn", false)
            .apply()

        setEmail("")
        setPassword("")
        setStayLoggedIn(false)
    }

    fun setEmail(email: String) {
        _loginState.value = loginState.value.copy(email = email)
    }

    fun setPassword(password: String) {
        _loginState.value = loginState.value.copy(password = password)
    }

    fun setStayLoggedIn(stayLoggedIn: Boolean) {
        _loginState.value = loginState.value.copy(stayLoggedIn = stayLoggedIn)
    }

}