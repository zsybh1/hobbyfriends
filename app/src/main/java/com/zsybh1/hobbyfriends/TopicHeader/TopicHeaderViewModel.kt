package com.zsybh1.hobbyfriends.TopicHeader

import android.util.Range
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.zsybh1.hobbyfriends.Const
import com.zsybh1.hobbyfriends.Holder.TopicDetailViewHolder
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Topic.TopicDetailViewModel
import com.zsybh1.hobbyfriends.Utils.NetUtil
import org.json.JSONObject
import kotlin.concurrent.thread

class TopicHeaderViewModel : ViewModel() {
    val dataListRecommend= mutableListOf<Topic>()
    val dataListTime= mutableListOf<Topic>()
    var pageTime:Int = 0
    var pageRecommend:Int = 0

    var result = 0
    fun getHeadersByTime(page : Int = pageTime){
        if (page == 0) {
            dataListTime.clear()
            pageTime = 0
        }
        val url = Const.apiHead + "/topics?size=${Const.pageSize}&page=${pageTime}&sort=sendDate,desc"
        val ret = NetUtil.getRequest(url)
        if (ret != null && ret[0] == '{') {
            val response = JSONObject(ret)
            val dataJson = response.getJSONObject("data")
            val contentArray = dataJson.getJSONArray("content")
            for (index in 0 until contentArray.length()) {
                val topic = Gson().fromJson(contentArray.get(index).toString(), Topic::class.java)
                dataListTime.add(topic)
            }
            result = 200
        }
        else {
            result = -1
        }
        pageTime++
    }

    fun getHeadersByRecommend(page: Int = pageRecommend){
        if (page == 0) {
            dataListRecommend.clear()
            pageRecommend = 0
        }
        val url = Const.apiHead + "/topics?size=${Const.pageSize}&page=${pageRecommend}&sort=likes,desc"
        val ret = NetUtil.getRequest(url)
        if (ret != null && ret[0] == '{') {
            val response = JSONObject(ret)
            val dataJson = response.getJSONObject("data")
            val contentArray = dataJson.getJSONArray("content")
            for (index in 0 until contentArray.length()) {
                val topic = Gson().fromJson(contentArray.get(index).toString(), Topic::class.java)
                dataListRecommend.add(topic)
            }
            result = 200
        }
        else {
            result = -1
        }
        pageRecommend++
    }
}