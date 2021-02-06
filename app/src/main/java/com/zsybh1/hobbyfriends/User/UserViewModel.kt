package com.zsybh1.hobbyfriends.User

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.zsybh1.hobbyfriends.Const
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Utils.NetUtil
import org.json.JSONObject
import java.time.LocalDateTime

class UserViewModel : ViewModel() {
    val dataListInvitation = mutableListOf<Invitation>()
    val dataListJoined = mutableListOf<Invitation>()
    val dataListTopic = mutableListOf<Topic>()

    var pageInvitation = 0
    var pageJoined = 0
    var pageTopic = 0

    var result = 0
    fun getInvitation(userId: Long, page:Int = pageInvitation ){
        if (page == 0) {
            pageInvitation = 0
            dataListInvitation.clear()
        }
        val url = Const.apiHead + "/me/${userId}/invites?size=${Const.pageSize}&page=${pageInvitation}&sort=likes,desc"
        val ret = NetUtil.getRequest(url)
        if (ret != null && ret[0] == '{') {
            val response = JSONObject(ret)
            val dataJson = response.getJSONObject("data")
            val contentArray = dataJson.getJSONArray("content")
            for (index in 0 until contentArray.length()) {
                val invitation = Gson().fromJson(contentArray.get(index).toString(), Invitation::class.java)
                dataListInvitation.add(invitation)
            }
            result = 200
        }
        else {
            result = -1
        }
        pageInvitation++
    }

    fun getJoined(userId: Long, page:Int = pageJoined){
        if (page == 0) {
            pageJoined = 0
            dataListJoined.clear()
        }
        val url = Const.apiHead + "/me/${userId}/invites?size=${Const.pageSize}&page=${pageJoined}&sort=likes,desc"
        val ret = NetUtil.getRequest(url)
        if (ret != null && ret[0] == '{') {
            val response = JSONObject(ret)
            val dataJson = response.getJSONObject("data")
            val contentArray = dataJson.getJSONArray("content")
            for (index in 0 until contentArray.length()) {
                val invitation = Gson().fromJson(contentArray.get(index).toString(), Invitation::class.java)
                dataListJoined.add(invitation)
            }
            result = 200
        }
        else {
            result = -1
        }
        pageJoined++
    }

    fun getTopic(userId: Long, page: Int = pageTopic){
        if (page == 0) {
            pageTopic = 0
            dataListTopic.clear()
        }
        val url = Const.apiHead + "/me/${userId}/topics?size=${Const.pageSize}&page=${pageTopic}&sort=likes,desc"
        val ret = NetUtil.getRequest(url)
        if (ret != null && ret[0] == '{') {
            val response = JSONObject(ret)
            val dataJson = response.getJSONObject("data")
            val contentArray = dataJson.getJSONArray("content")
            for (index in 0 until contentArray.length()) {
                val topic = Gson().fromJson(contentArray.get(index).toString(), Topic::class.java)
                dataListTopic.add(topic)
            }
            result = 200
        }
        else {
            result = -1
        }
        pageTopic++
    }
}