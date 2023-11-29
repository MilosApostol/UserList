package com.example.list.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListRepository @Inject constructor(private val dao: ListDao) {

<<<<<<< HEAD
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
=======
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
>>>>>>> ef376b2c1a92064e52d3931d31d09a66aeaa2524
}