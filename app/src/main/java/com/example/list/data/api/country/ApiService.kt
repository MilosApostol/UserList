package com.example.list.data.api.country

import com.example.list.Constants
import com.example.list.data.api.country.MyData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApiService {
    @GET(Constants.API_ENDPOINT)
    suspend fun getCountries(): List<MyData>

    @GET(Constants.API_ENDPOINT)
    suspend fun searchCountries(@Query("search") query: String): List<MyData>

    @GET("${Constants.API_ENDPOINT}/{code}")
    suspend fun getCountry(@Path("code") code: String): MyData
}