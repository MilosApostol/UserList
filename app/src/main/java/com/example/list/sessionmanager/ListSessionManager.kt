package com.example.list.sessionmanager

import com.example.list.data.list.ListEntity

class ListSessionManager{
    private var currentList: ListEntity? = null

    fun setList(list: ListEntity) {
        this.currentList = list
    }

    fun getListId(): Int? {
        return currentList?.id?.toInt()
    }

    fun getList(): ListEntity? {
        return currentList
    }


}