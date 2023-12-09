package com.example.list.predefinedlook

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen() {

    Scaffold(modifier = Modifier.background(Color.White)) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Row(modifier = Modifier.padding(8.dp)) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp)
                        )
                        Text(
                            text = "Information",
                            modifier = Modifier.padding(start = 6.dp),
                            style = TextStyle(fontSize = 12.sp)
                        )
                    }
                    Text(
                        text = "Calculate how much profit you would earn if you" +
                                " are to invest now and suppose the price goes higher"
                    )
                }

            }
            ElevatedCard(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedCard(border = BorderStroke(1.dp, Color.Black)) {
                        Row {

                        }
                    }

                }

            }

        }
    }
}

@Preview
@Composable
fun HistoryShow() {
    HistoryScreen()
}