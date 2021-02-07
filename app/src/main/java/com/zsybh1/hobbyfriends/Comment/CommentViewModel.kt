package com.zsybh1.hobbyfriends.Comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.zsybh1.hobbyfriends.Model.Comment

class CommentViewModel : ViewModel() {
    companion object {
        var selectId = 0L
        val selectName  = MutableLiveData<String>("")
    }
    var dataList = mutableListOf<Comment>()

    var topicId = 0L

    fun updateData(data: Comment) {
        dataList.clear()
        dataList.add(data)
        if (data.subComments !=null) {
            for (comment in data.subComments) {
                dataList.add(comment)
            }
        }
    }
}