package com.example.list.use_case

import com.example.list.data.ListEntity
import com.example.list.data.ListRepository

class DeleteList(private val repository: ListRepository) {
    suspend operator fun invoke(list: ListEntity){
        repository.deleteList(list)
    }
}