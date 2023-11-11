package com.example.list.usecase

import com.example.list.data.ListEntity
import com.example.list.data.ListRepository

class DeleteList(private val repository: ListRepository) {

    suspend fun invoke(list: ListEntity){
        repository.deleteList(list)
    }
}