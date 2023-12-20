package com.example.list.data.api.country

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
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
                it.name = country.name
                it.code = country.code
            } ?: run {
                _selectedCountries.addAll(listOf(country))
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun getCountries() {
        viewModelScope.launch {
            _country.value = getCountriesUseCase()
        }
    }
}
