package com.example.list.predefinedlook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.list.data.api.MyData

@Composable
fun CountryList(countries: List<MyData>, onItemClick: (MyData) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = countries, key = { country -> country.code }) { country ->
          //  CountryCard(item = country, onClick = { onItemClick(country) })
        }
    }
}