package com.example.list.data.api

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class CountryViewModel(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {
    private val _country = MutableStateFlow(emptyList<MyData>())
    val country: StateFlow<List<MyData>> = _country

    suspend fun getCountry() {
        _country.value = getCountriesUseCase()
    }
}