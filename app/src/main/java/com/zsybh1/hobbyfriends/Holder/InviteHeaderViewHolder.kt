package com.zsybh1.hobbyfriends.Holder

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Topic.TopicActivity
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_invite_header.view.*
import java.time.Duration
import java.time.LocalDateTime

class InviteHeaderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(data: Invitation, fragment: Fragment){
        view.tvTitle.visibility = View.GONE
        view.imPicture.visibility = View.GONE

        view.tvUsername.text =  data.username
        view.tvTime.text = TimeUtil.getRelativeTimeString(TimeUtil.getLDTfromString(data.sendDate))
        view.tvComment.text = data.comments.size.toString()
        view.tvContent.text = data.content?:""
        view.tvSub.text = data.likes.toString()

        if (data.title != null) {
            view.tvTitle.visibility = View.VISIBLE
            view.tvTitle.text = data.title
        }
        if (data.imgUrl.isNotEmpty()) {
            view.imPicture.visibility = View.VISIBLE
            BitmapUtil.display(view.imPicture, data.imgUrl[0])
        }

        BitmapUtil.display(view.imProfile, data.headImg)

        val now = LocalDateTime.now()
        val deadline = TimeUtil.getLDTfromString(data.activity.deadline)
        var duration = Duration.between(deadline, now)
        if (duration.isNegative) {
            view.tvJoinState.text = "可加入"
        }
        else {
            view.tvJoinState.text = "已截止"
        }
        val startTime = TimeUtil.getLDTfromString(data.activity.start)
        duration = Duration.between(startTime, now)
        if (duration.isNegative) {
            view.tvStartState.text = "未开始"
        }
        else {
            val endTime = TimeUtil.getLDTfromString(data.activity.end)
            duration = Duration.between(endTime, now)
            if (duration.isNegative) {
                view.tvStartState.text = "进行中"
            }
            else {
                view.tvStartState.text = "已结束"
            }
        }
        view.tvTag.text = data.activity.tag

        view.setOnClickListener {
            val intent = Intent(fragment.context, TopicActivity::class.java)
            intent.putExtra("topicId", data.id)
            intent.putExtra("type", "invitation")
            fragment.startActivity(intent)
        }
    }
}