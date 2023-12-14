package com.example.list.data.api.additemsapi

import com.example.list.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.API_ITEMS_ENDPOINT)
    suspend fun getItems(): List<AddItemsData>

    @GET(Constants.API_ITEMS_ENDPOINT)
    suspend fun searchItems(@Query("search") query: String): List<AddItemsData>

    @GET("${Constants.API_ITEMS_ENDPOINT}/{title}")
    suspend fun getItem(@Path("title") title: String): AddItemsData
}