package com.example.list.data.api.country

import com.example.list.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val gson = GsonBuilder().setLenient().create()
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

object ApiClient {
    val apiService: CountryApiService by lazy {
        RetrofitClient.retrofit.create(CountryApiService::class.java)
    }
}