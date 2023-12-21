package com.example.list.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalNetworkState = compositionLocalOf<Boolean> { error("No network state provided") }

@Composable
fun ProvideNetworkState(
    isNetworkAvailable: Boolean,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalNetworkState provides isNetworkAvailable,
        content = content
    )
}