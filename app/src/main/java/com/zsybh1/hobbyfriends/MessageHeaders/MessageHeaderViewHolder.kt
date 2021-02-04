package com.zsybh1.hobbyfriends.MessageHeaders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_message_header.view.*

class MessageHeaderViewHolder(private val view:View): RecyclerView.ViewHolder(view) {
    fun bind(header: MessageHeaderModel){
        view.tvUsername.text = header.username
        view.tvMessage.text = header.content
        view.tvTime.text = TimeUtil.getQQLikeTimeString(header.Time)
        BitmapUtil.display(view.imProfile, header.profile)
    }
}