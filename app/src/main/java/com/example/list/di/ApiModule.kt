package com.example.list.di

import com.example.list.data.api.ApiClient
import com.example.list.data.api.CountriesRep
import com.example.list.data.api.CountryApiService
import com.example.list.data.api.GetCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesCountryApiService(): CountryApiService {
        return ApiClient.apiService
    }

    @Provides
    @Singleton
    fun providesCountryRepository(countryApiService: CountryApiService): CountriesRep {
        return CountriesRep(countryApiService)
    }

    @Provides
    @Singleton
    fun providesGetCountriesUseCase(countryRepository: CountriesRep): GetCountriesUseCase {
        return GetCountriesUseCase(countryRepository)
    }
}