package com.example.list.usecase

data class ListUsecase(
    val getLists: GetListsUseCase,
    val deleteList: DeleteList
) {
}