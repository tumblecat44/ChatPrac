package com.leegeonhee.chatwithgpt

data class ChatRoom(
    val name: String,
    val participants: MutableList<String> = mutableListOf()
)

data class ChatMessage(
    val token: String,
    val message: String
)

data class ChatOnline(
    val writer : String,
    val message: String
)