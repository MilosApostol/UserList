package com.example.list.screens.tryout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.list.data.api.country.CountryViewModel
import com.example.list.data.api.country.MyData
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