package com.example.list.data.api.country


class GetCountriesUseCase(private val countryRepository: CountriesRep) {
    suspend operator fun invoke(): List<MyData> {
        return countryRepository.getCountries()
    }
}