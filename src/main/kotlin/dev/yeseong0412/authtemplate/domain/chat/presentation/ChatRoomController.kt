package com.leegeonhee.chatwithgpt

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatRoomController(val chatRoomService: ChatRoomService) {

    @GetMapping("/rooms")
    fun getAllRooms(): List<ChatRoom> = chatRoomService.getAllRooms()

    @PostMapping("/rooms")
    fun createRoom(@RequestParam name: String): ChatRoom = chatRoomService.createRoom(name)

    @PostMapping("/rooms/{roomId}/invite")
    fun inviteToRoom(@PathVariable roomId: String, @RequestParam participant: String): ChatRoom? =
        chatRoomService.inviteToRoom(roomId, participant)

    // 채팅방 입장
    @MessageMapping("/enter/{roomId}")
    @SendTo("/topic/room/{roomId}")
    fun enterRoom(@PathVariable roomId: String, username: String): String {
        return chatRoomService.enterRoom(roomId, username)
    }

    // 채팅방 퇴장
    @MessageMapping("/exit/{roomId}")
    @SendTo("/topic/room/{roomId}")
    fun exitRoom(@PathVariable roomId: String, username: String): String {
        return chatRoomService.exitRoom(roomId, username)
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/room/{roomId}")
    fun sendMessage(@PathVariable roomId: String, message: ChatMessage): ChatMessage {
        return message
    }

}
