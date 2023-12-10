package com.example.list.data.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiViewModel: ViewModel() {

    init {
        fetchData()
    }

    private fun fetchData(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = ApiRepository.fetchData()
            val dataList = ApiData.parseJson(data)
        }
    }
}