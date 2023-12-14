package com.example.list.data.additems

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AddItemsRepository @Inject constructor(
    private val dao: AddItemsDao
) {

    suspend fun getItems(): Flow<List<AddItemsCustom>> {
        return dao.getAll()
    }

    fun getItemsById(id: Int): Flow<AddItemsCustom> {
        return dao.getItemById(id)
    }

    suspend fun insertItem(item: AddItemsCustom) {
        dao.insertItem(item)
    }
    suspend fun deleteItem(item: AddItemsCustom) {
        dao.deleteItem(item)
    }
}