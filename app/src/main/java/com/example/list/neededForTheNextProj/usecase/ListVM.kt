package com.example.list.neededForTheNextProj.usecase

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.data.ListEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
@HiltViewModel
class ListVM @Inject constructor(
    private val listUseClass: ListUsecase
) : ViewModel() {
    private val _state = mutableStateOf(ListState())
    val state: State<ListState> = _state

    private var recentlyDeleted: ListEntity? = null

    private var getListJob: Job? = null

    init {
        viewModelScope.launch {
            getList(ListOrder.ListName(OrderType.Descending))
        }
    }

    fun onEvent(event: ListsEvent) {
        when (event) {
            is ListsEvent.Order -> {
                if (state.value.listOrder::class == event.listOrder::class &&
                    state.value.listOrder.orderType == event.listOrder.orderType
                ) {
                    return
                }
            }

            is ListsEvent.DeleteList -> {
                viewModelScope.launch {
                    recentlyDeleted = event.list
                    listUseClass.deleteList(event.list)
                }
            }

            is ListsEvent.RestoreList -> {
                viewModelScope.launch {
                    listUseClass.addList(recentlyDeleted ?: return@launch)
                    recentlyDeleted = null

                }
            }

            is ListsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private suspend fun getList(listOrder: ListOrder) {
        getListJob?.cancel()
        getListJob = listUseClass.getLists(listOrder)
            .onEach { lists ->
                _state.value = state.value.copy(
                    list = lists,
                    listOrder = listOrder
                )
            }
            .launchIn(viewModelScope)
    }

 */