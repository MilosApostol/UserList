package com.example.list.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

//The use of object instead of class for ApiRepository in the example denotes a singleton pattern,
// appropriate for a stateless repository with functions mainly for data retrieval, simplifying usage without the need for multiple instances.
object ApiRepository {
    suspend fun fetchData(): String {
        return withContext(Dispatchers.IO) {
            val url = URL("https://run.mocky.io/v3/6c347c00-6bbf-480b-b044-d5a2bb99293b")
            val connection = url.openConnection() as HttpURLConnection

            return@withContext try {
                val inputStream = connection.inputStream
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                val stringBuilder = StringBuilder()

                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }

                stringBuilder.toString()
            } finally {
                connection.disconnect()
            }
        }
    }
}