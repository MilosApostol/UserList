package com.example.list.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

data class MyData(
    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String
)
data class Name(
    @SerializedName("common")
    val common: String
)
