package com.zsybh1.hobbyfriends.TopicHeaders

import androidx.lifecycle.ViewModel
import com.zsybh1.hobbyfriends.InviteHeaders.InviteHeaderModel
import java.time.LocalDateTime

class TopicHeaderViewModel : ViewModel() {
    fun getHeadersByTime() : List<TopicHeaderModel>{
        // TODO: 发送请求，获取json并解析
        val headers = mutableListOf<TopicHeaderModel>()
        headers.add(TopicHeaderModel(1, LocalDateTime.now(), null, "测试用户",
                "麻了", null, "https://img-blog.csdn.net/column?imageView2/1/w/224/h/224/interlace/1", 0, 0))
        headers.add(TopicHeaderModel(2, LocalDateTime.now().minusMinutes(24), "网传北理工理教发生自杀事件，是真的吗？发生什么了？", "连城",
                "这大学是为以后在拼多多工作打基础吗？？\n记得我大三这个时候 做机械设计大作业\n每天晚上肝到两点起步，连肝三个星期。节点检查的前一晚，去理教通宵，推开门一看全都是我们院的同学拿着丁字尺，佝偻着腰趴在桌子上。\n画一晚上 第二天早上直接去教室检查。\n交完大作业 紧接着就是机械设计考试和汽车构造考试划重点 划了一节课我记得有一天晚上复习到十一点半出门一看下雪了操场上大家在堆雪人 打雪仗 还有情侣挽着手遛弯没敢多玩 就回宿舍看书了。我还记得当年我很气愤地跟同学说：连个A0图纸和设计说明书都要上淘宝买，大学白读了。前几天我在学弟三点发的朋友圈底下评论：淘宝买一个呗，又不贵", "https://pic1.zhimg.com/50/v2-970c43645f17c5a7adb79b5aa554d303_hd.jpg?source=1940ef5c", "https://pic1.zhimg.com/v2-28dba1409d3cba94335bf511c713ae91_xl.jpg", 233, 123))

        return headers.toList()
    }

    fun getHeadersByRecommend() : List<TopicHeaderModel>{
        // TODO: 发送请求，获取json并解析
        return getHeadersByTime()
    }
}