package com.leegeonhee.chatwithgpt
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class ChatRoomService {
    private val chatRooms: MutableMap<String, ChatRoom> = ConcurrentHashMap()

    fun getAllRooms(): List<ChatRoom> = chatRooms.values.toList()

    fun createRoom(name: String): ChatRoom {
        val room = ChatRoom(id = chatRooms.size.toString(), name = name)
        chatRooms[room.id] = room
        return room
    }

    fun inviteToRoom(roomId: String, participant: String): ChatRoom? {
        val room = chatRooms[roomId]
        room?.participants?.add(participant)
        return room
    }

    fun enterRoom(roomId: String, username: String): String {
        return "$username 님이 $roomId 방에 입장하셨습니다."

    }

    fun exitRoom(roomId: String, username: String): String {
        return "$username 님이 $roomId 방에서 퇴장하셨습니다."
    }
}
