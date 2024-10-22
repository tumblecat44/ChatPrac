package dev.yeseong0412.authtemplate.domain.chat.domain

import dev.yeseong0412.authtemplate.domain.chat.domain.entity.ChatRoomEntity
import org.springframework.data.jpa.repository.JpaRepository
import kotlin.jvm.internal.Ref.LongRef

interface ChatRoomRepository : JpaRepository<ChatRoomEntity,Long> {
}