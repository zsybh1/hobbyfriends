package com.zsybh1.hobbyfriends.Holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Comment.CommentViewModel
import com.zsybh1.hobbyfriends.Model.Comment
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_comment.view.*

import java.lang.StringBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CommentDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(data: Comment) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val time = LocalDateTime.parse(data.sendDate, formatter)
        view.tvTime.text = TimeUtil.getRelativeTimeString(time)
        view.tvSub.text = data.likes.toString()
        view.tvComment.text = data.context
        view.imProfile.setImageResource(R.mipmap.default_image)

        BitmapUtil.display(view.imProfile, data.headImg)
        fun buildReply() : String {
            val stringBuilder = StringBuilder()
            stringBuilder.append(data.username)
            stringBuilder.append(" 回复 ")
            stringBuilder.append(data.replyToName)
            return stringBuilder.toString()
        }
        if (data.replyToName != null && data.replyToName != "") {
            view.tvUsername.text = buildReply()
        }
        else {
            view.tvUsername.text = data.username
        }
        view.setOnClickListener {
            CommentViewModel.selectId = data.userId
            CommentViewModel.selectName.value = data.username
        }
    }
}