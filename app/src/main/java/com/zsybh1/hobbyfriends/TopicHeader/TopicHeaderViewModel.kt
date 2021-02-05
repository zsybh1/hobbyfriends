package com.zsybh1.hobbyfriends.TopicHeader

import androidx.lifecycle.ViewModel
import com.zsybh1.hobbyfriends.Holder.TopicDetailViewHolder
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Topic.TopicDetailViewModel

class TopicHeaderViewModel : ViewModel() {
    fun getHeadersByTime() : List<Topic>{
        // TODO: 发送请求，获取json并解析
        val headers = mutableListOf<Topic>()
        headers.add(TopicDetailViewModel().getTopic())
        return headers.toList()
    }

    fun getHeadersByRecommend() : List<Topic>{
        // TODO: 发送请求，获取json并解析
        return getHeadersByTime()
    }
}