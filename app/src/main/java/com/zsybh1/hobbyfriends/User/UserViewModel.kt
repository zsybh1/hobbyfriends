package com.zsybh1.hobbyfriends.User

import androidx.lifecycle.ViewModel
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Model.Topic
import java.time.LocalDateTime

class UserViewModel : ViewModel() {
    fun getInvitation(userId: Long) : MutableList<Invitation> {
        val headers = mutableListOf<Invitation>()
        return headers
    }

    fun getJoined(userId: Long) : MutableList<Invitation> {
        return  getInvitation(userId)
    }

    fun getTopic(userId: Long) : List<Topic> {
        val headers = mutableListOf<Topic>()
        return headers.toList()
    }
}