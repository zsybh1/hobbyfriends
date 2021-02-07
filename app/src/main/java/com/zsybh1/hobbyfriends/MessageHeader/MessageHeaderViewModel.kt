package com.zsybh1.hobbyfriends.MessageHeader

import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

class MessageHeaderViewModel : ViewModel() {
    fun getHeaders(): List<MessageHeaderModel>{
        val headers = mutableListOf<MessageHeaderModel>()
        headers.add(MessageHeaderModel(1, "冰灵", "很高兴认识你", LocalDateTime.now(), "https://img-blog.csdn.net/column?imageView2/1/w/224/h/224/interlace/1"))
        headers.add(MessageHeaderModel(2, "zaga", "可还行", LocalDateTime.now().minusMinutes(1), "https://pic4.zhimg.com/v2-5243701beafa25f1434f5e7809060139_xs.jpg?source=1940ef5c"))
        return headers.toList()
    }
}