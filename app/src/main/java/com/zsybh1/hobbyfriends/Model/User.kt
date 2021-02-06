package com.zsybh1.hobbyfriends.Model

data class User(
    val id: Long,
    val username: String,
    val pwd: String,
    val gender: String,
    val email: String,
    val headImgUrl: String?
)
