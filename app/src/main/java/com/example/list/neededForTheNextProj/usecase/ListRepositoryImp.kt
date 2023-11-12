package com.example.list.neededForTheNextProj.usecase

import com.example.list.data.ListDao
import com.example.list.data.ListEntity
import kotlinx.coroutines.flow.Flow
/*
class ListRepositoryImp(private val dao: ListDao): ListRepository {
    override suspend fun getLists(): Flow<List<ListEntity>> {
        return dao.getAll()
    }

    override suspend fun getListById(id: Int): List<ListEntity> {
        return dao.getListId(id)
    }

    override suspend fun insertList(list: ListEntity) {
        dao.insertList(list)
    }

    override suspend fun updateList(list: ListEntity) {
        dao.updateList(list)
    }

    override suspend fun deleteList(list: ListEntity) {
        dao.deleteList(list)
    }
}

 */