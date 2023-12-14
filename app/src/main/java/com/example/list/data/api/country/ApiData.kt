package com.example.list.data.api.country

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

data class MyData(
    @SerializedName("name")
    var name: String,
    @SerializedName("code")
    var code: String
)