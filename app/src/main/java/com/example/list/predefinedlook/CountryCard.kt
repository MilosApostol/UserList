package com.example.list.predefinedlook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.list.data.api.MyData

@Composable
fun CountryCard(item: MyData,  onCountryClick: (MyData) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Gray)
            .clickable(onClick = { onCountryClick(item) })
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = "Name: ${item.name}",
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "Region: ${item.code}",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}
