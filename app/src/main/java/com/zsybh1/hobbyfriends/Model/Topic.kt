package com.zsybh1.hobbyfriends.Model


data class Topic(
    val id: Long,
    val sendDate: String,
    val username: String,
    val ownerId : Long,
    val headImg: String?,
    val imgUrl : List<String>,
    val comments : List<Comment>?,
    val title:String,
    val context: String,
    val views: Long?,
    val likes: Long?,
    val likeList: List<Long?>?
)
