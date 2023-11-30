package com.example.list.predefinedlook

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.list.navigation.Screen

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.White
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp, horizontal = 8.dp)
        .background(background)
        .clickable {
            onDrawerItemClicked()
        }) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = null,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(text = item.dTitle, style = MaterialTheme.typography.headlineMedium)

    }

}
