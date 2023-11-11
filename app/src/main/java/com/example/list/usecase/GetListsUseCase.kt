package com.example.list.usecase

import com.example.list.data.ListEntity
import com.example.list.data.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


//giving order to the list
class GetListsUseCase(private val repository: ListRepository) {
    suspend operator fun invoke(listOrder: ListOrder = ListOrder.ListName(OrderType.Descending)): Flow<List<ListEntity>>{
            return repository.getLists().map {lists->
                when(listOrder.orderType){
                    is OrderType.Ascending -> {
                        when(listOrder){
                            is ListOrder.ListName -> lists.sortedBy { it.list?.lowercase() }
                         //   is Index.Order.Date -> lists.sortedBy { it.timestamp }
                        //    is Index.Order.Color -> lists.sortedBy { it.color } if you have date or color
                        }
                    }
                    is OrderType.Descending ->{
                        when(listOrder){
                            is ListOrder.ListName -> lists.sortedByDescending { it.list?.lowercase() }
                            //   is Index.Order.Date -> lists.sortedByDescending { it.timestamp }
                            //    is Index.Order.Color -> lists.sortedByDescending { it.color } if you have date or color
                        }
                    }
                }
            }
    }
}