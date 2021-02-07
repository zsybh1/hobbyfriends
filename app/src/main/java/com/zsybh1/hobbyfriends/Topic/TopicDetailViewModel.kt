package com.zsybh1.hobbyfriends.Topic

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.zsybh1.hobbyfriends.Const
import com.zsybh1.hobbyfriends.Model.Comment
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Model.User
import com.zsybh1.hobbyfriends.Utils.NetUtil
import org.json.JSONObject

class TopicDetailViewModel : ViewModel() {
    var topicId = 0L

    val dataList = mutableListOf<Any>()

    var result = 0
    companion object{
        private const val TAG = "TopicViewModel"
    }

    fun getTopic(type:String){
        dataList.clear()
        val res = getTopicFromNet(type)
        if (res != "") {
            if (type == "topic") {
                dataList.add(Gson().fromJson(res, Topic::class.java))
                Log.d(TAG, dataList[0].toString())
                if ((dataList[0] as Topic).comments != null) {
                    for (comment in (dataList[0] as Topic).comments!!) {
                        dataList.add(comment.apply { comment.type = type })
                    }
                }
            }
            else {
                dataList.add(Gson().fromJson(res, Invitation::class.java))
                Log.d(TAG, dataList[0].toString())
                if ((dataList[0] as Invitation).comments != null) {
                    for (comment in (dataList[0] as Invitation).comments!!) {
                        dataList.add(comment.apply { comment.type = type })
                    }
                }
            }
        }

    }

    fun getTopicFromNet(type: String) : String{
        val ret = NetUtil.getRequest(Const.apiHead + "/${type}/${topicId}")
        if (ret != null && ret[0] == '{') {
            val response = JSONObject(ret)
            val dataJson = response.getJSONObject("data")
            result = 200
            return dataJson.toString()
        }
        else {
            result = 0
            return ""
        }
    }
}