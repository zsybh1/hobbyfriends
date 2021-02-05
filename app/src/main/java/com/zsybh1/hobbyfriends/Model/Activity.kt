package com.zsybh1.hobbyfriends.Model

data class Activity(
    val tag: String,
    val deadline: String,
    val start: String,
    val end: String,
    val position: String,
    val followCount: Int,
    val followers: List<User>
)
