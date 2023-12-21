package com.example.list.screens.tryout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen() {

    var coinPrice by remember {
        mutableStateOf("")
    }

    Scaffold(modifier = Modifier.background(Color.White)) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
            ElevatedCard(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedCard(border = BorderStroke(1.dp, Color.Black)) {
                        Text(text = "Enter current crypto price:")

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.QuestionMark,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(16.dp)
                            )
                            Row(modifier = Modifier.clickable { }) {
                                Text(text = "BTC", style = TextStyle(fontSize = 16.sp))
                                Icon(
                                    imageVector = Icons.Default.ArrowDownward,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .width(12.dp)
                                        .height(12.dp)
                                )
                            }
                            TextField(
                                modifier = Modifier.background(Color.Transparent),

                                value = coinPrice,
                                placeholder = { Text("Price") },
                                onValueChange = { coinPrice = it },
                            )

                        }
                    }
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(text = "Enter amount of investment")

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.QuestionMark,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(16.dp)
                            )
                            TextField(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .fillMaxWidth(),

                                value = coinPrice,
                                placeholder = { Text("Price") },
                                onValueChange = { coinPrice = it },
                            )

                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Enter future price:",
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .background(color = Color.Gray)
                                .size(35.dp, 15.dp)
                        ) {
                            Text(
                                text = "+10%",
                                modifier = Modifier.background(color = Color.Gray)
                            )
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .background(color = Color.Gray)
                                .size(35.dp, 15.dp)
                        ) {
                            Text(
                                text = "+50%",
                                fontSize = 12.sp
                            )// Adjust the text size as needed

                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .background(color = Color.Gray)
                                .size(35.dp, 15.dp)
                        ) {
                            Text(
                                text = "x2",
                                fontSize = 12.sp
                            ) // Adjust the text size as needed

                        }
                    }

                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {

                        Row {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.QuestionMark,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(16.dp)
                                        .height(16.dp)
                                )
                                TextField(
                                    modifier = Modifier
                                        .background(Color.Transparent)
                                        .fillMaxWidth(),

                                    value = coinPrice,
                                    placeholder = { Text("Price") },
                                    onValueChange = { coinPrice = it },
                                )
                            }
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