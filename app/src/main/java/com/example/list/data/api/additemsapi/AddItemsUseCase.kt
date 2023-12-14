package com.example.list.data.api.additemsapi

import javax.inject.Inject

class AddItemsUseCase @Inject constructor(private val repository: AddItemsRepository) {

    suspend fun getItems(): List<AddItemsData> {
        return repository.getItems()
    }

    suspend fun getItem(title: String): AddItemsData {
        return repository.getItem(title)
    }
}