package com.zsybh1.hobbyfriends.InviteHeader

import androidx.lifecycle.ViewModel
import com.zsybh1.hobbyfriends.Model.Invitation

class InviteHeaderViewModel : ViewModel() {
    val dataList = mutableListOf<Invitation>()

    var currentPage = 0

    fun getFirstPage(pageSize: Int = 10) {
        dataList.clear()
        currentPage = 0

    }

    fun getMorePage(pageSize:Int = 10){
        currentPage++
    }
}