package com.example.list.data

import kotlinx.coroutines.flow.Flow

class Repository (private val dao: ListDao) {

    suspend fun getLists(): Flow<List<ListEntity>> {
        return dao.getAll()
    }

    suspend fun getListById(id: Int): List<ListEntity>{
        return dao.getListId(id)
    }

    suspend fun insertList(list: ListEntity){
        dao.insertList(list)
    }

    suspend fun updateList(list: ListEntity){
        dao.updateList(list)
    }

    suspend fun deleteList(list: ListEntity){
        dao.deleteList(list)
    }
}