package com.example.list.use_case

import com.example.list.data.ListEntity
import com.example.list.data.ListRepository

class GetList(
    private val repository: ListRepository
) {

    suspend operator fun invoke(id: Int): ListEntity {
        return repository.getListById(id)
    }
}