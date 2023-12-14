package com.example.list.data.api.additemsapi

import com.example.list.Constants
import com.example.list.data.api.country.CountryApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val gson = GsonBuilder().setLenient().create()
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL_Items)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

object ApiItemsClient {
    val apiService: ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}