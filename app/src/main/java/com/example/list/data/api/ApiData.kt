package com.example.list.data.api

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class MyData(val name: String, val code: String)

object ApiData {
    fun parseJson(json: String): List<MyData> {
        return Json.decodeFromString<List<MyData>>(json)
    }
}
