package com.zsybh1.hobbyfriends.Model

data class Invitation(
    val id: Long,
    val sendDate: String,
    val ownerName: String,
    val ownerId : Long,
    val headImg: String,
    val imgUrl : List<String>,
    val comments : List<Comment>?,
    val title:String?,
    val context: String,
    val views: Long?,
    val likes: Long?,
    val likeList: List<Long?>?,
    val activity: Activity
)
