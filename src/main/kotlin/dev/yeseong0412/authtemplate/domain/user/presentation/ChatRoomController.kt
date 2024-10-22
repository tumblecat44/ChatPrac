package dev.yeseong0412.authtemplate.domain.user.presentation

import com.leegeonhee.chatwithgpt.ChatMessage
import com.leegeonhee.chatwithgpt.ChatOnline
import com.leegeonhee.chatwithgpt.ChatRoom
import dev.yeseong0412.authtemplate.domain.chat.service.ChatRoomService
import dev.yeseong0412.authtemplate.global.auth.jwt.JwtUtils
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/chat")
class ChatRoomController(val chatRoomService: ChatRoomService,val jwtUtils: JwtUtils) {

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
    fun sendMessage(@PathVariable roomId: String, message: ChatMessage): ChatOnline {
        val toMessage = ChatOnline(writer =  jwtUtils.getUsername(message.token),message=message.message)
        return toMessage
    }

}
