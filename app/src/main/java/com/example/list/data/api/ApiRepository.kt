package com.example.list.data.api

import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object ApiConstants {
    const val BASE_URL = "https://run.mocky.io/"
    const val API_ENDPOINT = "v3/6c347c00-6bbf-480b-b044-d5a2bb99293b/"
}

interface CountryApiService{
    @GET(ApiConstants.API_ENDPOINT)
    suspend fun getCountries(): List<MyData>

    @GET(ApiConstants.API_ENDPOINT)
    suspend fun searchCountries(@Query("search") query: String): List<MyData>

}

object RetrofitClient {
    private const val BASE_URL = ApiConstants.BASE_URL

    val gson = GsonBuilder().setLenient().create()
    val retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

object ApiClient {
    val apiService: CountryApiService by lazy {
        RetrofitClient.retrofit.create(CountryApiService::class.java)
    }
}