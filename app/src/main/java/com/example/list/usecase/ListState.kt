package com.example.list.usecase

import com.example.list.data.ListEntity

data class ListState(
    val list: List<ListEntity> = emptyList(),
    val listOrder: ListOrder = ListOrder.ListName(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
