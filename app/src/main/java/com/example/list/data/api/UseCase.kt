package com.example.list.data.api


class GetCountriesUseCase(private val countryRepository: CountriesRep) {
    suspend operator fun invoke(): List<MyData> {
        return countryRepository.getCountries()
    }
}