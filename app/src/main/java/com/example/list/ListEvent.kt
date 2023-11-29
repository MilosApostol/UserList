package com.example.list

import com.example.list.data.ListEntity

sealed class ListEvent{
    data class DeleteList(val list: ListEntity): ListEvent()
    data class AddList(val list: ListEntity): ListEvent()
    object RestoreList: ListEvent()

}
