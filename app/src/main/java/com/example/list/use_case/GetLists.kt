package com.example.list.use_case

import com.example.list.data.ListEntity
import com.example.list.data.ListRepository
import kotlinx.coroutines.flow.Flow

class GetLists(
    private val repository: ListRepository
) {

    suspend operator fun invoke() : Flow<ListEntity> {
        return repository.getLists()
    }
}