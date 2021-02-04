package com.zsybh1.hobbyfriends.TopicHeaders

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Topic.TopicActivity
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_topic_header.view.imPicture
import kotlinx.android.synthetic.main.item_topic_header.view.imProfile
import kotlinx.android.synthetic.main.item_topic_header.view.tvComment
import kotlinx.android.synthetic.main.item_topic_header.view.tvContent
import kotlinx.android.synthetic.main.item_topic_header.view.tvSub
import kotlinx.android.synthetic.main.item_topic_header.view.tvTime
import kotlinx.android.synthetic.main.item_topic_header.view.tvTitle
import kotlinx.android.synthetic.main.item_topic_header.view.tvUsername

class TopicHeaderViewHolder(private val fragment: Fragment, private val view : View) : RecyclerView.ViewHolder(view){
    fun bind(header:TopicHeaderModel){
        view.setOnClickListener{
            val intent = Intent(fragment.activity, TopicActivity::class.java)
            intent.putExtra("topicId", header.id)
            fragment.startActivity(intent)
        }
        view.tvUsername.text =  header.username
        view.tvTime.text = TimeUtil.getRelativeTimeString(header.time)
        view.tvComment.text = header.commentNum.toString()
        view.tvContent.text = header.content?:""
        view.tvSub.text = header.subNum.toString()
        if (header.title != null) {
            view.tvTitle.visibility = View.VISIBLE
            view.tvTitle.text = header.title
        }
        if (header.image != null) {
            view.imPicture.visibility = View.VISIBLE
            BitmapUtil.display(view.imPicture, header.image)
        }
        BitmapUtil.display(view.imProfile, header.profile)
    }
}