package com.example.list.data.additems

import com.example.list.data.api.CountryApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine


/*
class CountryRepository(countryApiService: CountryApiService) {

    // Simulating data retrieval from a data source
    private val countries = listOf(
        country
    )

    private val _countriesList = MutableStateFlow(countries)

    fun getCountriesFilteredBySearchText(searchText: String): Flow<List<List<String>>> {
        return _countriesList.combine(MutableStateFlow(searchText)) { countries, text ->
            if (text.isBlank()) {
                countries
            } else {
                countries.filter { country ->
                    country.contains(text.trim().uppercase())
                }
            }
        }
    }
}


 */