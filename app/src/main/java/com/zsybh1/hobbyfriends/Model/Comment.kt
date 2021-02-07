package com.zsybh1.hobbyfriends.Model

data class Comment(
    val commentId: Long,
    val replyToId: Long,
    var replyToName: String?,
    val context: String,
    val userId: Long,
    val username: String,
    val headImg: String,
    val likes: Long?,
    val subComments: List<Comment>?,
    val sendDate: String,
    var type: String?
)
