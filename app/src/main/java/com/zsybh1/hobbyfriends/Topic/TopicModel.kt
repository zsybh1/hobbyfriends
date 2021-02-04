package com.zsybh1.hobbyfriends.Topic

import java.time.LocalDateTime

interface TopicModel

data class Topic(
        val id: Long,
        val sendDate: String,
        val username: String,
        val ownerId : Long,
        val headImg: String,
        val imgUrl : List<String>,
        val comments : List<Comment>,
        val title:String?,
        val content: String,
        val views: Long,
        val likes: Long,
        val isLiked: Boolean
):TopicModel

data class Comment(
        val commentId: Long,
        val replyToId: Long,
        val replyToName: String?,
        val content: String,
        val userId: Long,
        val username: String,
        val headImg: String,
        val likes: Long,
        val subComments: List<Comment>,
        val sendDate: String
):TopicModel