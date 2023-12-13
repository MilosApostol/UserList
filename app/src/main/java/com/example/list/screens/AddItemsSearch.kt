package com.example.list.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.list.data.api.CountryViewModel
import com.example.list.data.api.MyData
import com.example.list.predefinedlook.CountryList
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun AddItemsSearch(
    countriesViewModel: CountryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var selectedText by remember { mutableStateOf("") }
    var listOfCountries by remember { mutableStateOf(mutableStateListOf<MyData>()) }
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    var jsonData by remember { mutableStateOf<String?>(null) }
    var scope = rememberCoroutineScope()
 //   val searchResults by countriesViewModel.searchResults.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        scope.launch {
            countriesViewModel.getCountries()
        }
    }
    val country by countriesViewModel.country.collectAsState()
    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .semantics { isTraversalGroup = true }) {
            SearchScreen(
          //      searchQuery = countriesViewModel.searchQuery,
          //      searchResults = searchResults,
          //      onSearchQueryChange = { countriesViewModel.setSelectedCountry(it) }
            )
        }
    }
}

@Preview
@Composable
fun addItems() {
    AddItemsSearch()
}