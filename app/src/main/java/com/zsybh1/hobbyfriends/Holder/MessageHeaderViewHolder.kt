package com.zsybh1.hobbyfriends.Holder

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.MessageHeader.MessageHeaderModel
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_message_header.view.*

class MessageHeaderViewHolder(private val view:View): RecyclerView.ViewHolder(view) {
    fun bind(header: MessageHeaderModel, fragment:Fragment){
        view.tvUsername.text = header.username
        view.tvMessage.text = header.content
        view.tvTime.text = TimeUtil.getQQLikeTimeString(header.Time)
        BitmapUtil.display(view.imProfile, header.profile)
        view.setOnClickListener {
            Toast.makeText(fragment.context, "该功能尚未完成！", Toast.LENGTH_LONG).show()
        }
    }
}