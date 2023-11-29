package com.example.list.use_case

data class ListUseCase(
    val getList: GetList,
    val addList: AddList,
    val getLists: GetLists,
    val deleteList: DeleteList
)