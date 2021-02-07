package com.zsybh1.hobbyfriends.Holder

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Const
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Topic.TopicActivity
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.NetUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_invite_header.view.*
import kotlinx.android.synthetic.main.item_topic_header.view.*
import kotlinx.android.synthetic.main.item_topic_header.view.imPicture
import kotlinx.android.synthetic.main.item_topic_header.view.imProfile
import kotlinx.android.synthetic.main.item_topic_header.view.imSub
import kotlinx.android.synthetic.main.item_topic_header.view.tvComment
import kotlinx.android.synthetic.main.item_topic_header.view.tvContent
import kotlinx.android.synthetic.main.item_topic_header.view.tvSub
import kotlinx.android.synthetic.main.item_topic_header.view.tvTime
import kotlinx.android.synthetic.main.item_topic_header.view.tvTitle
import kotlinx.android.synthetic.main.item_topic_header.view.tvUsername
import kotlin.concurrent.thread

class TopicHeaderViewHolder(private val view : View) : RecyclerView.ViewHolder(view){
    fun bind(data:Topic, fragment: Fragment){
        view.tvTitle.visibility = View.GONE
        view.imPicture.visibility = View.GONE
        view.imProfile.setImageResource(R.mipmap.default_image)
        view.imPicture.setImageResource(R.mipmap.default_image)


        view.tvUsername.text =  data.username
        view.tvTime.text = TimeUtil.getRelativeTimeString(TimeUtil.getLDTfromString(data.sendDate))
        view.tvComment.text = if (data.comments !=null) (data.comments.size).toString() else "0"
        view.tvContent.text = data.context
        view.tvSub.text = if (data.likeList != null) data.likeList.size.toString() else "0"
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
                    NetUtil.postRequest(Const.apiHead + "/topic/${data.id}/like?userId=${userid}", "")
                }
                else {
                    NetUtil.deleteRequest(Const.apiHead + "/topic/${data.id}/like?userId=${userid}", "")
                }
            }
        }

        if (data.title != "") {
            view.tvTitle.visibility = View.VISIBLE
            view.tvTitle.text = data.title
        }
        if (data.imgUrl.isNotEmpty()) {
            view.imPicture.visibility = View.VISIBLE
            BitmapUtil.display(view.imPicture, data.imgUrl[0])
        }
        if (data.headImg != null) {
            BitmapUtil.display(view.imProfile, data.headImg)
        }

        view.setOnClickListener {
            val intent = Intent(fragment.context, TopicActivity::class.java)
            intent.putExtra("topicId", data.id)
            intent.putExtra("type", "topic")
            fragment.startActivity(intent)
        }
    }
}