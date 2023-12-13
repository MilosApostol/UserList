package com.example.list.data.api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.data.api.ApiClient.apiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val repository: CountriesRep
) :
    ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _country = MutableStateFlow(listOf<MyData>())
    private val zemlje = listOf<MyData>()

    val country = searchText
        .combine(_country) { text, country ->
            if (text.isBlank()) {
                country
            } else {
                country.filter { countryName ->
                    countryName.name.contains(text, ignoreCase = true)

                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _country.value
        )


    private var _selectedCountry = mutableStateListOf<MyData?>(null)
    val selectedCountry: List<MyData?> = _selectedCountry


    private val _selectedCountries = mutableStateListOf<MyData>()
    val selectedCountries: List<MyData> get() = _selectedCountries
    fun addToSelectedCountries(country: MyData) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingCountry = selectedCountries.find { it.code == country.code }
            existingCountry?.let {
                // Update the existing country with relevant properties from the provided one
                it.name = country.name
                it.code = country.code
                // ... Update other properties as needed
            } ?: run {
                // If not found, add the new country
                _selectedCountries.addAll(listOf(country))
            }
            // Process the updated list as needed
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    lateinit var getAllCountries: List<MyData>

    init {
        viewModelScope.launch {
            getAllCountries = repository.getCountries()
        }
    }

    /*
        fun setSelectedCountry(country: String) {
            selectedCountries = country
        }



     */
    fun getCountries() {
        viewModelScope.launch {
            _country.value = getCountriesUseCase()
        }
    }
}

