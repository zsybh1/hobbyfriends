package com.zsybh1.hobbyfriends.TopicHeaders

import java.time.LocalDateTime

data class TopicHeaderModel(
    val id: Long,
    val time: LocalDateTime,
    val title: String?,
    val username: String,
    val content: String?,
    val image: String?,     //图片id
    val profile: String,    //头像id
    val commentNum: Int,
    val subNum: Int
)