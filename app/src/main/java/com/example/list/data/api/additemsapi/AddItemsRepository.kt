package com.example.list.data.api.additemsapi

import javax.inject.Inject


class AddItemsRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getItems(): List<AddItemsData> {
        return apiService.getItems()
    }

    suspend fun getItem(title: String): AddItemsData{
        return apiService.getItem(title)
    }

}