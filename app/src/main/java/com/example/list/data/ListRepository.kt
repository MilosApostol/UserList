package com.example.list.data

import kotlinx.coroutines.flow.Flow

interface ListRepository {
    suspend fun getLists(): Flow<List<ListEntity>>

    suspend fun getListById(id: Int): List<ListEntity>

    suspend fun insertList(list:ListEntity)

    suspend fun updateList(list: ListEntity)

    suspend fun deleteList(list: ListEntity)
}