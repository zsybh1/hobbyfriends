package com.zsybh1.hobbyfriends.Holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_invitation_detail.view.*
import java.time.Duration
import java.time.LocalDateTime

class InvitationDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(header: Invitation, fragment: Fragment) {
        view.tvTitle.visibility = View.GONE
        view.imageLayout.visibility = View.GONE
        view.imageLayout.removeAllViews()
        BitmapUtil.display(view.imProfile, header.headImg)
        view.tvUsername.text = header.ownerName

        view.tvTime.text = TimeUtil.getRelativeTimeString(TimeUtil.getLDTfromString(header.sendDate))
        if (header.title != "") {
            view.tvTitle.visibility = View.VISIBLE
            view.tvTitle.text = header.title
        }
        view.tvContent.text = header.context.replace("\n", "\n\n")
        view.tvComment.text = if (header.comments != null) header.comments.size.toString() else "0"
        view.tvSub.text = (header.likes?:0).toString()
        if (header.imgUrl.isNotEmpty()) {
            view.imageLayout.visibility = View.VISIBLE
            for (url in header.imgUrl) {
                val imageView = ImageView(view.context)
                imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                BitmapUtil.display(imageView, url)
                view.imageLayout.addView(imageView)
            }
        }

        view.tvPlace.text = "地点：${header.activity.position}"
        view.tvTag.text = "类型：${header.activity.tag}"
        view.tvCount.text = "人数：${if (header.comments != null) header.comments.size else 0}/${header.activity.followCount}"
        view.tvDeadline.text = "参加截止时间：${header.activity.deadline}"
        view.tvStartTime.text = "活动开始时间：${header.activity.start}"
        view.tvEndTime.text = "活动结束时间：${header.activity.end}"
        val username = fragment.requireActivity().getSharedPreferences("save", Context.MODE_PRIVATE).getString("username", "")
        var isFollowed = false
        if (header.activity.followers != null) {
            for (user in header.activity.followers) {
                if (username == user.username) {
                    isFollowed = true
                    break
                }
            }
        }
        if (isFollowed) {
            view.tvJoin.text = "退出"
        }
        else {
            view.tvJoin.text = "加入"
        }

        val now = LocalDateTime.now()
        val deadline = TimeUtil.getLDTfromString(header.activity.deadline)
        val duration = Duration.between(now, deadline)

        view.tvJoin.setOnClickListener {
            if (!isFollowed && duration.isNegative) {
                Toast.makeText(fragment.context, "活动已截止，无法加入！", Toast.LENGTH_LONG).show()
            }
            else if (!isFollowed && (header.activity.followers == null || header.activity.followers.size <= header.activity.followCount)) {
                Toast.makeText(fragment.context, "活动已满员，无法加入！", Toast.LENGTH_LONG).show()
            }
            else if (!isFollowed) {
                //TODO:加入
            }
            else {
                //TODO: 退出
            }
        }
    }
}