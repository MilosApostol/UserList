package com.example.list.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListRepository @Inject constructor(private val dao: ListDao) {

    suspend fun getLists(): Flow<ListEntity> {
        return dao.getAll()
    }

    suspend fun getListById(id: Int): ListEntity {
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