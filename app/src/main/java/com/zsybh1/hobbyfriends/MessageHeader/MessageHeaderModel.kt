package com.zsybh1.hobbyfriends.MessageHeader

import java.time.LocalDateTime

data class MessageHeaderModel(
    val id: Int,
    val username: String,
    val content: String?,
    val Time: LocalDateTime,
    val profile: String
)