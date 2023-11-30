package com.example.list.data.itemlists

import androidx.lifecycle.ViewModel
import com.example.list.data.list.ListEntity
import com.example.list.data.list.ListRepository
import com.example.list.sessionmanager.ListSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class ListsItemViewModel @Inject constructor(
    private val listRepository: ListRepository,
    private val listSessionManager: ListSessionManager
) : ViewModel() {

    fun getItemsByListId(): Flow<ListEntity> {
        return listSessionManager.getList()?.id?.let { listRepository.getListById(it) }
            ?: emptyFlow()
    }

    fun getList(): Int?{
        return listSessionManager.getList()?.id
    }
}