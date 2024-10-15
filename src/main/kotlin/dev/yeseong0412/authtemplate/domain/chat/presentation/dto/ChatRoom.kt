package com.leegeonhee.chatwithgpt

data class ChatRoom(
    val id: String,
    val name: String,
    val participants: MutableList<String> = mutableListOf()
)

data class ChatMessage(
    val username: String,
    val message: String
)