package com.zsybh1.hobbyfriends.Model

data class RequestTopic(
    val sendDate: String,
    val ownerId : Long,
    val imgUrl : List<String>,
    val title:String,
    val context: String
)

data class RequestInvitation(
    val ownerId: Long,
    val imgUrl: List<String>,
    val tag: String,
    val title: String,
    val context: String,
    val sendDate: String,
    val activity: Activity
)

data class RequestComment(
    val replyToId: Long,
    val userId:Long,
    val context: String,
    val sendDate: String
)