package com.example.list.data.api

import javax.inject.Inject

class CountriesRep @Inject constructor(val apiService: CountryApiService) {
    suspend fun getCountries(): List<MyData> {
        return apiService.getCountries()
    }
}

