package com.zsybh1.hobbyfriends.User

import androidx.lifecycle.ViewModel
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Model.Topic
import java.time.LocalDateTime

class UserViewModel : ViewModel() {
    val dataListInvitation = mutableListOf<Invitation>()
    val dataListJoined = mutableListOf<Invitation>()
    val dataListTopic = mutableListOf<Topic>()

    var pageInvitation = 0
    var pageJoined = 0
    var pageTopic = 0

    fun getInvitation(userId: Long, page:Int = pageInvitation ){
        if (page == 0) {
            pageInvitation = 0
            dataListInvitation.clear()
        }
        pageInvitation++
    }

    fun getJoined(userId: Long, page:Int = pageJoined){
        if (page == 0) {
            pageJoined = 0
            dataListJoined.clear()
        }
        pageJoined++
    }

    fun getTopic(userId: Long, page: Int = pageTopic){
        if (page == 0) {
            pageTopic = 0
            dataListTopic.clear()
        }
        pageTopic++
    }
}