package com.zsybh1.hobbyfriends.Holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Model.Comment
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_comment.view.*

import java.lang.StringBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CommentDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(data: Comment) {
        BitmapUtil.display(view.imProfile, data.headImg)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val time = LocalDateTime.parse(data.sendDate, formatter)
        view.tvTime.text = TimeUtil.getRelativeTimeString(time)
        view.tvComment.text = data.content.replace("\n", "\n\n")
        view.tvSub.text = data.likes.toString()
        fun buildReply() : String {
            val stringBuilder = StringBuilder()
            stringBuilder.append(data.username)
            stringBuilder.append(" 回复 ")
            stringBuilder.append(data.replyToName)
            return stringBuilder.toString()
        }
        if (data.replyToName != null) {
            view.tvUsername.text = buildReply()
        }
        else {
            view.tvUsername.text = data.username
        }
    }
}