package com.example.list.data.api

import javax.inject.Inject


class GetCountriesUseCase (private val countryRepository: CountriesRep) {
    suspend operator fun invoke(): List<MyData> {
        return countryRepository.getCountries()
    }

    suspend fun getCountry(country: String): List<MyData>{
        return countryRepository.getCountry(country)
    }
}