package com.zsybh1.hobbyfriends.MessageHeaders

import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

class MessageHeaderViewModel : ViewModel() {
    fun getHeaders(): List<MessageHeaderModel>{
        // TODO: 发送请求，获取json并解析
        val headers = mutableListOf<MessageHeaderModel>()
        headers.add(MessageHeaderModel(1, "睿信书院", "通知", LocalDateTime.now(), "https://img-blog.csdn.net/column?imageView2/1/w/224/h/224/interlace/1"))
        headers.add(MessageHeaderModel(2, "zga", "233", LocalDateTime.now().minusMinutes(1), "https://img-blog.csdn.net/column?imageView2/1/w/224/h/224/interlace/1"))
        return headers.toList()
    }
}