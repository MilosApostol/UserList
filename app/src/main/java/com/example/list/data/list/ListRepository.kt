package com.example.list.data.list

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListRepository @Inject constructor(private val dao: ListDao) {

    suspend fun getLists(): Flow<List<ListEntity>> {
        return dao.getAll()
    }

     fun getListById(id: Int): Flow<ListEntity> {
        return dao.getListId(id)
    }

    suspend fun insertList(list: ListEntity) {
        dao.insertList(list)
    }

    suspend fun updateList(list: ListEntity) {
        dao.updateList(list)
    }

    suspend fun deleteList(list: ListEntity) {
        dao.deleteList(list)
    }
    suspend fun deleteAllLists() {
        dao.deleteAllLists()
    }
}