package com.zsybh1.hobbyfriends.Topic

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_comment.view.*

import java.lang.StringBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CommentViewHolder(private val fragment: Fragment, private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(header: Comment) {
        BitmapUtil.display(view.imProfile, header.headImg)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val time = LocalDateTime.parse(header.sendDate, formatter)
        view.tvTime.text = TimeUtil.getRelativeTimeString(time)
        view.tvComment.text = header.content.replace("\n", "\n\n")
        view.tvSub.text = header.likes.toString()
        fun buildReply() : String {
            val stringBuilder = StringBuilder()
            stringBuilder.append(header.username)
            stringBuilder.append(" 回复 ")
            stringBuilder.append(header.replyToName)
            return stringBuilder.toString()
        }
        if (header.replyToName != null) {
            view.tvUsername.text = buildReply()
        }
        else {
            view.tvUsername.text = header.username
        }
    }
}