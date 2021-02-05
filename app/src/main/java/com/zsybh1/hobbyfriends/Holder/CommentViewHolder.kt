package com.zsybh1.hobbyfriends.Holder

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Model.Comment
import com.zsybh1.hobbyfriends.Topic.CommentFragment
import com.zsybh1.hobbyfriends.Topic.VpTopicAdapter
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.activity_topic.*
import kotlinx.android.synthetic.main.fragment_comment.view.*
import kotlinx.android.synthetic.main.item_comment.view.*
import java.lang.StringBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CommentViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(data: Comment, fragment:Fragment, topicId: Long) {
        BitmapUtil.display(view.imProfile, data.headImg)
        view.replyLayout.visibility = View.GONE
        view.tvReply1.visibility = View.GONE
        view.tvReply2.visibility = View.GONE
        view.tvReply3.visibility = View.GONE
        view.tvMore.visibility = View.GONE

        view.tvUsername.text = data.username
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val time = LocalDateTime.parse(data.sendDate, formatter)
        view.tvTime.text = TimeUtil.getRelativeTimeString(time)
        view.tvComment.text = data.content.replace("\n", "\n\n")
        view.tvSub.text = data.likes.toString()
        fun buildReply(index: Int) : String {
            val stringBuilder = StringBuilder()
            stringBuilder.append(data.subComments[index].username)
            stringBuilder.append(" 回复 ")
            stringBuilder.append(data.subComments[index].replyToName)
            stringBuilder.append("：")
            stringBuilder.append(data.subComments[index].content)
            return stringBuilder.toString()
        }
        if (data.subComments.isNotEmpty()) {
            view.replyLayout.visibility = View.VISIBLE
            view.replyLayout.setOnClickListener {
                val viewpager = fragment.requireActivity().vpTopic
                while ((viewpager.adapter as VpTopicAdapter).fragments.size >= 2)
                    (viewpager.adapter as VpTopicAdapter).delFragment()
                (viewpager.adapter as VpTopicAdapter).addFragment(
                    CommentFragment.newInstance(
                        topicId,
                        data.commentId
                    )
                )
                viewpager.setCurrentItem(1, true)
            }
            view.tvReply1.visibility = View.VISIBLE
            view.tvReply1.text =  buildReply(0)
        }
        if (data.subComments.size > 1) {
            view.tvReply2.visibility = View.VISIBLE
            view.tvReply2.text = buildReply(1)
        }
        if (data.subComments.size > 2) {
            view.tvReply3.visibility = View.VISIBLE
            view.tvReply3.text = buildReply(2)
        }
        if (data.subComments.size > 3) {
            view.tvMore.visibility = View.VISIBLE
            view.tvMore.text = "共${data.subComments.size}条回复"
        }
    }
}
