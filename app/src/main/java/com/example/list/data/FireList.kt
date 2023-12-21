package com.example.list.data

import android.content.ClipData.Item
import com.example.list.data.api.additemsapi.AddItemsData
import com.example.list.data.firebase.items.Items
import com.example.list.data.list.ListEntity

data class FireList(
    val user: com.example.list.data.userdata.User,
    val list: ListEntity,
    val item: Items,
) {
}