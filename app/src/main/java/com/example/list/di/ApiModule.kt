package com.example.list.di

import com.example.list.data.api.additemsapi.AddItemsRepository
import com.example.list.data.api.additemsapi.AddItemsUseCase
import com.example.list.data.api.additemsapi.ApiItemsClient
import com.example.list.data.api.additemsapi.ApiService
import com.example.list.data.api.country.ApiClient
import com.example.list.data.api.country.CountriesRep
import com.example.list.data.api.country.CountryApiService
import com.example.list.data.api.country.GetCountriesUseCase
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

    @Provides
    @Singleton
    fun providesAddItemsService(): ApiService {
        return ApiItemsClient.apiService
    }
    @Provides
    @Singleton
    fun providesAddItemsRepository(apiService: ApiService): AddItemsRepository{
        return AddItemsRepository(apiService)
    }

    @Provides
    @Singleton
    fun providesAddItemsUseCase(addItemsRepository: AddItemsRepository): AddItemsUseCase{
        return AddItemsUseCase(addItemsRepository)
    }
}