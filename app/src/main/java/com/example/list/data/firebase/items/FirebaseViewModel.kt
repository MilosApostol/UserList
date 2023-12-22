package com.example.list.data.firebase.items

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.data.list.ListEntity
import com.example.list.sessionmanager.UserSessionManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val repository: FirebaseRepository,
) :
    ViewModel() {
    private val _state = MutableStateFlow(Items())
    val state = _state.asStateFlow()

    lateinit var getAllItems: Flow<List<Items>>


    init {
        viewModelScope.launch {
            getAllItems = repository.getItemsFlow()
        }
    }


    fun fetchData() {
        viewModelScope.launch {
            repository.fetchData()
        }
    }

    fun cancelFetch() {
        repository.cancelFetch()
    }

    fun removeItem(itemId: String) {
        repository.removeItem(itemId)
    }

    fun removeAll(itemCreatorId: String) {
        repository.removeItems(itemCreatorId)
    }

    fun onNetworkRestored() {
        fetchData()
    }
/*
    fun loginUser(auth: FirebaseAuth, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        return@addOnCompleteListener,
                        "Welcome back $email",
                        Toast.LENGTH_LONG
                    ).show()
                }

                if (user != null) {
                    userSessionManager.setUser(user = user)
                }
                user?.let {
                    return@withContext it.password == password
                }
                return@withContext false
            }
        }
    }

 */
}
