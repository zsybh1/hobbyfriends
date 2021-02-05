package com.zsybh1.hobbyfriends.TopicHeader

import androidx.lifecycle.ViewModel
import com.zsybh1.hobbyfriends.Holder.TopicDetailViewHolder
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Topic.TopicDetailViewModel

class TopicHeaderViewModel : ViewModel() {
    val dataListRecommend= mutableListOf<Topic>()
    val dataListTime= mutableListOf<Topic>()
    var pageTime:Int = 0
    var pageRecommend:Int = 0
    fun getHeadersByTime(page : Int = pageTime){
        if (page == 0) {
            dataListTime.clear()
            pageTime = 0
        }
        pageTime++
        // TODO: 发送请求，获取json并解析
        dataListTime.add(TopicDetailViewModel().apply { getTopic() }.dataList[0] as Topic)
    }

    fun getHeadersByRecommend(page: Int = pageRecommend){
        if (page == 0) {
            dataListRecommend.clear()
            pageRecommend = 0
        }
        pageRecommend++
        // TODO: 发送请求，获取json并解析
        dataListRecommend.add(TopicDetailViewModel().apply { getTopic() }.dataList[0] as Topic)
    }
}