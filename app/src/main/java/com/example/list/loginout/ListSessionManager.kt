package com.example.list.loginout

import com.example.list.data.Items
import com.example.list.data.ListEntity
import com.example.list.data.User
import kotlinx.coroutines.flow.MutableStateFlow

class ListSessionManager{
    private var currentList: ListEntity? = null

    fun setList(list: ListEntity) {
        this.currentList = list
    }

    fun getListId(): Int? {
        return currentList?.id
    }

    fun getList(): ListEntity? {
        return currentList
    }

}