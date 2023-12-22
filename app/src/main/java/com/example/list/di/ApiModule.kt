package com.example.list.di

import android.content.Context
import com.example.list.data.api.additemsapi.AddItemsRepository
import com.example.list.data.api.additemsapi.AddItemsUseCase
import com.example.list.data.api.additemsapi.ApiItemsClient
import com.example.list.data.api.additemsapi.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.prefs.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

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