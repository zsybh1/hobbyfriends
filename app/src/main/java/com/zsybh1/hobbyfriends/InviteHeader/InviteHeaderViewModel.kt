package com.zsybh1.hobbyfriends.InviteHeader

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.zsybh1.hobbyfriends.Const
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Utils.NetUtil
import org.json.JSONObject

class InviteHeaderViewModel : ViewModel() {
    val dataList = mutableListOf<Invitation>()

    var currentPage = 0

    var result = 0
    fun getPage(page: Int = currentPage) {
        if (page == 0) {
            dataList.clear()
            currentPage = 0
        }
        val url = Const.apiHead + "/invitations?tag=all&size=${Const.pageSize}&page=${currentPage}&sort=sendDate,desc"
        val ret = NetUtil.getRequest(url)
        if (ret != null && ret[0] == '{') {
            val response = JSONObject(ret)
            val dataJson = response.getJSONObject("data")
            val contentArray = dataJson.getJSONArray("content")
            for (index in 0 until contentArray.length()) {
                val invitation = Gson().fromJson(contentArray.get(index).toString(), Invitation::class.java)
                dataList.add(invitation)
            }
            result = 200
        }
        else {
            result = -1
        }
        currentPage++
    }
}