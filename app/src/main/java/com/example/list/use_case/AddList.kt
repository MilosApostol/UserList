package com.example.list.use_case

import com.example.list.data.ListEntity
import com.example.list.data.ListRepository

class AddList(private val repository: ListRepository) {

    suspend operator fun invoke(list: ListEntity){
        if (list.listName?.isBlank() == true){
            repository.insertList(ListEntity(0, listName = "New List"))
        }
        list.listName?.let { repository.insertList(list = ListEntity(0, listName = list.toString())) }
    }
}