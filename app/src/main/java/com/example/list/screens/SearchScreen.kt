package com.example.list.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.list.data.api.CountryViewModel
import com.example.list.data.api.MyData
import com.example.list.predefinedlook.CountryCard
import com.example.list.predefinedlook.items
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    countriesViewModel: CountryViewModel = hiltViewModel(),
) {
    var scope = rememberCoroutineScope()
    var active by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val isSearching by countriesViewModel.isSearching.collectAsState()
    val country by countriesViewModel.country.collectAsState()
    val selectedCountries = countriesViewModel.selectedCountries

    LaunchedEffect(Unit) {
        scope.launch {
            countriesViewModel.getCountries()
        }
    }
    DockedSearchBar(
        modifier = Modifier
            .padding(top = 8.dp)
            .semantics { traversalIndex = -1f },
        query = text,
        onQueryChange = {
            text = it
            countriesViewModel.onSearchTextChange(text)
        },
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text("Hinted search text") },
        leadingIcon = {
            if (!active) {
                Icon(Icons.Default.Search, contentDescription = null)
            } else {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable { active = false })
            }
        },
        trailingIcon = {
            if (isSearching) {
                Icon(Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        // Handle closing search or clearing query if needed
                    }
                )
            }
        }
    ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(country) { country ->
                    CountryCard(item = country) {
                        countriesViewModel.addToSelectedCountries(country)
                    }
                }
            }
            if (selectedCountries.isNotEmpty()) {
                Text(text = "Selected countries")
                selectedCountries.forEach {
                    Text(text = it.name)
                }
            }

    }
}
