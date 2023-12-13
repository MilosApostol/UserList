package com.example.list.data.api

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountriesRep @Inject constructor(
    private val apiService: CountryApiService) {
    suspend fun getCountries(): List<MyData> {
        return apiService.getCountries()
    }

    suspend fun getCountry(country: String): List<MyData> {
        return apiService.searchCountries(country)
    }

    suspend fun getCountryByCode(code: String): MyData {
        return apiService.getCountry(code)
    }

}

