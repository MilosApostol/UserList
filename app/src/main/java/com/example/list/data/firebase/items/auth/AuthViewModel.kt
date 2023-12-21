package com.example.list.data.firebase.items.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.sealed.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
@HiltViewModel
class UserSignupViewModel @Inject constructor(
    private val application: Application,
    private val repository: AuthRepositoryImpl
) : AndroidViewModel(application = application) {

    private var _registerState = MutableStateFlow<RegisterState>(value = RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.registerUser(email = email, password = password).collectLatest { result ->
            when (result) {
                is Resource.Loading -> {
                    _registerState.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _registerState.update { it.copy(isSuccess = "Register Successful!") }
                }

                is Resource.Error -> {
                    _registerState.update { it.copy(isError = result.message) }
                }
            }
        }
    }


}

 */