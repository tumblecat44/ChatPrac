package dev.yeseong0412.authtemplate.domain.chat.domain.service
import com.leegeonhee.chatwithgpt.ChatRoom
import dev.yeseong0412.authtemplate.domain.chat.domain.ChatRoomRepository
import dev.yeseong0412.authtemplate.domain.chat.domain.entity.ChatRoomEntity
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class ChatRoomService(private val chatRoomRepository: ChatRoomRepository) {
    fun getAllRooms(): MutableList<ChatRoomEntity> = chatRoomRepository.findAll()

    fun createRoom(name: String): ChatRoomEntity {
        val room = ChatRoom(name = name)
        val saveRoom = ChatRoomEntity(name = room.name, participants = room.participants)
        chatRoomRepository.save(saveRoom)
        return saveRoom
    }

//    fun inviteToRoom(roomId: String, participant: String): ChatRoom? {
//        val room = chatRoomRepository.findById(roomId.toLong())
//        room?.let {
//            // participants 리스트가 null이 아닌지 확인 후 참가자 추가
//            it.participants?.add(participant) ?: run {
//                // participants가 null일 경우 리스트 초기화 후 참가자 추가
//                it.participants = mutableListOf(participant)
//            }
//
//            // 변경 사항 저장
//            chatRoomRepository.save(it)
//        }
//        return room
//    }

    fun enterRoom(roomId: String, username: String): String {
        return "$username 님이 $roomId 방에 입장하셨습니다."

    }

    fun exitRoom(roomId: String, username: String): String {
        return "$username 님이 $roomId 방에서 퇴장하셨습니다."
    }
}
