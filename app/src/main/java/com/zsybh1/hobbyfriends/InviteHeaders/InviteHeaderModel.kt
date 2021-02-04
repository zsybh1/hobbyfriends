package com.zsybh1.hobbyfriends.InviteHeaders

import java.time.LocalDateTime

interface IInviteModel

data class InviteHeaderModel(
    val id: Long,
    val time: LocalDateTime,
    val title: String?,
    val username: String,
    val content: String?,
    val image: String?,     //图片id
    val profile: String,    //头像id
    val commentNum: Int,
    val subNum: Int
) : IInviteModel

data class JoinedInvitationModel(
    val id: Long,
    val time: LocalDateTime,
    val title: String
) : IInviteModel