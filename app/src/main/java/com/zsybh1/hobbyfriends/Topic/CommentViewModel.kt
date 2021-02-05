package com.zsybh1.hobbyfriends.Topic

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.zsybh1.hobbyfriends.Model.Comment

class CommentViewModel : ViewModel() {
    var topicId = 0L
    var commentId = 0L

    fun getComment() : Comment {
        val Comment = Gson().fromJson(getCommentFromNet(), Comment::class.java)
        return Comment
    }

    fun getCommentFromNet() :String{
        return """
            {
                "commentId": 1235,
                "replyToId": 0,
                "replyToName": null,
                "userId": 3,
                "username": "jack",
                "content": "为什么非得保,自己考不行吗？",
                "headImg": "https://pic4.zhimg.com/da8e974dc_im.jpg",
                "likes": 19,
                "isLiked": false,
                "sendDate": "2021-01-13 19:28:37",
                "subComments": [
                    {                
                        "commentId": 1236,
                        "replyToId": 3,
                        "replyToName": "jack",
                        "userId": 4,
                        "username": "Richard",
                        "content": "说明你没有参加过考研",
                        "headImg": "https://pic4.zhimg.com/v2-064e11d554e917b1d6501454a969417b_im.jpg",
                        "likes": 50,
                        "isLiked": false,
                        "sendDate": "2021-01-13 19:29:37",
                        "subComments": []
                    },
                    {                
                        "commentId": 1237,
                        "replyToId": 4,
                        "replyToName": "Richard",
                        "userId": 3,
                        "username": "jack",
                        "content": "这么武断，我都研究生毕业了[捂脸]",
                        "headImg": "https://pic4.zhimg.com/da8e974dc_im.jpg",
                        "likes": 39,
                        "isLiked": false,
                        "sendDate": "2021-01-13 19:30:37",
                        "subComments": []
                    },
                    {                
                        "commentId": 1238,
                        "replyToId": 3,
                        "replyToName": "jack",
                        "userId": 5,
                        "username": "我要把你做得好次",
                        "content": "那你岂不是更不了解如今考研的难度了？",
                        "headImg": "https://pic3.zhimg.com/v2-8fa9c3832b410ea0b28ccdd6f3f45c64_im.jpg",
                        "likes": 33,
                        "isLiked": false,
                        "sendDate": "2021-01-13 19:31:37",
                        "subComments": []
                    },
                    {                
                        "commentId": 1237,
                        "replyToId": 5,
                        "replyToName": "我要把你做得好次",
                        "userId": 3,
                        "username": "jack",
                        "content": "你觉得保研非文化课占比很大，那考研可都是文化课，你真有实力还考不上[好奇]",
                        "headImg": "https://pic4.zhimg.com/da8e974dc_im.jpg",
                        "likes": 14,
                        "isLiked": false,
                        "sendDate": "2021-01-13 19:30:37",
                        "subComments": []
                    }
                ]
            }
        """.trimIndent()
    }
}