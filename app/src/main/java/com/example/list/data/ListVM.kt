package com.example.list.data

import androidx.lifecycle.ViewModel
import com.example.list.usecase.ListUsecase
import com.example.list.usecase.ListsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ListVM @Inject constructor(
    private val listUseClass: ListUsecase): ViewModel(){

        fun onEvent(event: ListsEvent){
            when(event){
                is Lis
            }
        }
    }