package com.example.list.internet


sealed class ConnectionState(
) {
    object Available: ConnectionState()
    object Unavailable: ConnectionState()
}