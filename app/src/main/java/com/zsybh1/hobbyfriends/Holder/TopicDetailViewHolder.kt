package com.zsybh1.hobbyfriends.Holder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_topic_detail.view.*
import kotlinx.android.synthetic.main.item_topic_detail.view.imProfile
import kotlinx.android.synthetic.main.item_topic_detail.view.tvComment
import kotlinx.android.synthetic.main.item_topic_detail.view.tvSub
import kotlinx.android.synthetic.main.item_topic_detail.view.tvTime
import kotlinx.android.synthetic.main.item_topic_detail.view.tvUsername

class TopicDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(header: Topic) {
        view.tvTitle.visibility = View.GONE
        view.imageLayout.visibility = View.GONE
        view.imageLayout.removeAllViews()
        BitmapUtil.display(view.imProfile, header.headImg)
        view.tvUsername.text = header.username

        view.tvTime.text = TimeUtil.getRelativeTimeString(TimeUtil.getLDTfromString(header.sendDate))
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
                val imageView = ImageView(view.context)
                imageView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                BitmapUtil.display(imageView, url)
                view.imageLayout.addView(imageView)
            }
        }
    }
}