package com.example.list.usecase

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
