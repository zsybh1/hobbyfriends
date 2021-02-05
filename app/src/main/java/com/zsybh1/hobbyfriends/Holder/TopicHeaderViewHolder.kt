package com.zsybh1.hobbyfriends.Holder

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_topic_header.view.*

class TopicHeaderViewHolder(private val fragment: Fragment, private val view : View) : RecyclerView.ViewHolder(view){
    fun bind(data:Topic){
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
    }
}