package com.example.list.data.api.additemsapi

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class AddItemsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("price")
    var price: String = ""
)