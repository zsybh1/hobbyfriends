package com.zsybh1.hobbyfriends.Topic

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.activity_topic.*
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_topic_detail.view.*
import kotlinx.android.synthetic.main.item_topic_detail.view.imProfile
import kotlinx.android.synthetic.main.item_topic_detail.view.tvComment
import kotlinx.android.synthetic.main.item_topic_detail.view.tvSub
import kotlinx.android.synthetic.main.item_topic_detail.view.tvTime
import kotlinx.android.synthetic.main.item_topic_detail.view.tvUsername
import java.lang.StringBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TopicDetailViewHolder(private val fragment: Fragment, private val view: View, private val topicId: Long) : RecyclerView.ViewHolder(view){
    fun bind(header: TopicModel) {
        when(header){
            is Topic -> {
                BitmapUtil.display(view.imProfile, header.headImg)
                view.tvUsername.text = header.username
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val time = LocalDateTime.parse(header.sendDate, formatter)
                view.tvTime.text = TimeUtil.getRelativeTimeString(time)
                if (header.title != null) {
                    view.tvTitle.visibility = View.VISIBLE
                    view.tvTitle.text = header.title
                }
                view.tvContent.text = header.content.replace("\n", "\n\n")
                view.tvComment.text = header.comments.size.toString()
                view.tvSub.text = header.likes.toString()
                if (header.imgUrl.isNotEmpty()) {
                    view.imageLayout.visibility = View.VISIBLE
                    for (url in header.imgUrl) {
                        val imageView = ImageView(fragment.activity)
                        imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                        BitmapUtil.display(imageView, url)
                        view.imageLayout.addView(imageView)
                    }
                }
            }
            is Comment -> {
                BitmapUtil.display(view.imProfile, header.headImg)
                view.tvUsername.text = header.username
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                val time = LocalDateTime.parse(header.sendDate, formatter)
                view.tvTime.text = TimeUtil.getRelativeTimeString(time)
                view.tvComment.text = header.content.replace("\n", "\n\n")
                view.tvSub.text = header.likes.toString()
                fun buildReply(index: Int) : String {
                    val stringBuilder = StringBuilder()
                    stringBuilder.append(header.subComments[index].username)
                    stringBuilder.append(" 回复 ")
                    stringBuilder.append(header.subComments[index].replyToName)
                    stringBuilder.append("：")
                    stringBuilder.append(header.subComments[index].content)
                    return stringBuilder.toString()
                }
                if (header.subComments.isNotEmpty()) {
                    view.replyLayout.visibility = View.VISIBLE
                    view.replyLayout.setOnClickListener {
                        val viewpager = fragment.requireActivity().vpTopic
                        while ((viewpager.adapter as vpTopicAdapter).fragments.size >= 2)
                            (viewpager.adapter as vpTopicAdapter).delFragment()
                        (viewpager.adapter as vpTopicAdapter).addFragment(CommentFragment.newInstance(topicId, header.commentId))
                        viewpager.setCurrentItem(1, true)
                    }
                    view.tvReply1.visibility = View.VISIBLE
                    view.tvReply1.text =  buildReply(0)
                }
                if (header.subComments.size > 1) {
                    view.tvReply2.visibility = View.VISIBLE
                    view.tvReply2.text = buildReply(1)
                }
                if (header.subComments.size > 2) {
                    view.tvReply3.visibility = View.VISIBLE
                    view.tvReply3.text = buildReply(2)
                }
                if (header.subComments.size > 3) {
                    view.tvMore.visibility = View.VISIBLE
                    view.tvMore.text = "共${header.subComments.size}条回复"
                }
            }
        }
    }
}