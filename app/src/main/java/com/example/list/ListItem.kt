package com.example.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.list.data.ListEntity

@Composable
fun ListItems(
    list: ListEntity,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                //todo
            }
            .border(1.dp, Color.Black),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = list.listName, fontWeight = FontWeight.ExtraBold)
        }
    }
}