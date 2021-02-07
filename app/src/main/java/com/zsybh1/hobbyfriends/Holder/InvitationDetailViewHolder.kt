package com.zsybh1.hobbyfriends.Holder

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Const
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.NetUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_invitation_detail.view.*
import java.time.Duration
import java.time.LocalDateTime
import kotlin.concurrent.thread

class InvitationDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(data: Invitation, fragment: Fragment) {
        view.tvTitle.visibility = View.GONE
        view.imageLayout.visibility = View.GONE
        view.imageLayout.removeAllViews()
        view.imProfile.setImageResource(R.mipmap.default_image)

        BitmapUtil.display(view.imProfile, data.headImg)
        view.tvUsername.text = data.ownerName
        var isliked = false
        val userid = fragment.requireActivity().getSharedPreferences("save", Context.MODE_PRIVATE).getLong("userid", 0L)
        if (data.likeList != null) {
            isliked = userid in data.likeList
        }
        if (isliked) {
            view.imSub.setImageResource(R.drawable.ic_baseline_thumb_up_24)
        }
        else {
            view.imSub.setImageResource(R.drawable.ic_outline_thumb_up_24)
        }

        view.imSub.setOnClickListener {
            isliked = !isliked
            if (isliked) {
                view.imSub.setImageResource(R.drawable.ic_baseline_thumb_up_24)
                view.tvSub.text = (view.tvSub.text.toString().toInt() + 1).toString()
            }
            else {
                view.imSub.setImageResource(R.drawable.ic_outline_thumb_up_24)
                view.tvSub.text = (view.tvSub.text.toString().toInt() - 1).toString()
            }
            thread {
                if (isliked) {
                    NetUtil.postRequest(Const.apiHead + "/invitation/${data.id}/like?userId=${userid}", "")
                }
                else {
                    NetUtil.deleteRequest(Const.apiHead + "/invitation/${data.id}/like?userId=${userid}", "")
                }
            }
        }
        view.tvTime.text = TimeUtil.getRelativeTimeString(TimeUtil.getLDTfromString(data.sendDate))
        if (data.title != "") {
            view.tvTitle.visibility = View.VISIBLE
            view.tvTitle.text = data.title
        }
        view.tvContent.text = data.context.replace("\n", "\n\n")
        view.tvComment.text = if (data.comments != null) data.comments.size.toString() else "0"
        view.tvSub.text = (data.likes?:0).toString()
        if (data.imgUrl.isNotEmpty()) {
            view.imageLayout.visibility = View.VISIBLE
            for (url in data.imgUrl) {
                val imageView = ImageView(view.context)
                val dm = view.resources.displayMetrics
                val width = dm.widthPixels
                BitmapUtil.display(imageView, url)
                var bitmap = BitmapUtil.memoryCacheUtil.getBitmap(url)
                if (bitmap == null) {
                    bitmap = BitmapUtil.localCacheUtil.getBitmap(url)
                }
                val height = width *bitmap!!.height / bitmap.width
                imageView.layoutParams = LinearLayout.LayoutParams(width,height)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                view.imageLayout.addView(imageView)
            }
        }

        view.tvPlace.text = "地点：${data.activity.position}"
        view.tvTag.text = "类型：${data.activity.tag}"
        var followers = if (data.activity.followers != null) data.activity.followers.size else 0
        view.tvCount.text = "人数：${followers}/${data.activity.followCount}"
        view.tvDeadline.text = "参加截止时间：${data.activity.deadline}"
        view.tvStartTime.text = "活动开始时间：${data.activity.start}"
        view.tvEndTime.text = "活动结束时间：${data.activity.end}"
        var isFollowed = false
        if (data.activity.followers != null) {
            for (user in data.activity.followers) {
                if (userid == user?.id) {
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
        val deadline = TimeUtil.getLDTfromString(data.activity.deadline)
        val duration = Duration.between(now, deadline)

        view.tvJoin.setOnClickListener {
            if (!isFollowed && data.ownerId == userid) {
                Toast.makeText(fragment.context, "不可加入自己发起的活动！", Toast.LENGTH_LONG).show()
            }
            else if (!isFollowed && duration.isNegative) {
                Toast.makeText(fragment.context, "活动已截止，无法加入！", Toast.LENGTH_LONG).show()
            }
            else if (!isFollowed && (followers >= data.activity.followCount)) {
                Toast.makeText(fragment.context, "活动已满员，无法加入！", Toast.LENGTH_LONG).show()
            }
            else if (!isFollowed) {
                isFollowed = !isFollowed
                view.tvJoin.text = "退出"
                followers++
                view.tvCount.text = "人数：${followers}/${data.activity.followCount}"
                thread {
                    NetUtil.postRequest(Const.apiHead + "/invitation/${data.id}/join?userId=${userid}", "")
                }
            }
            else {
                isFollowed = !isFollowed
                view.tvJoin.text = "加入"
                followers--
                view.tvCount.text = "人数：${followers}/${data.activity.followCount}"
                thread {
                    NetUtil.deleteRequest(
                        Const.apiHead + "/invitation/${data.id}/join?userId=${userid}",
                        ""
                    )
                }
            }
        }
    }
}