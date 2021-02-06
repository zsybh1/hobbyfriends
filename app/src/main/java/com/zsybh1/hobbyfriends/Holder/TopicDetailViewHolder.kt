package com.zsybh1.hobbyfriends.Holder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_topic_detail.view.*

class TopicDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(header: Topic) {
        view.tvTitle.visibility = View.GONE
        view.imageLayout.visibility = View.GONE
        view.imageLayout.removeAllViews()
        if (header.headImg != null) {
            BitmapUtil.display(view.imProfile, header.headImg)
        }
        view.tvUsername.text = header.username

        view.tvTime.text = TimeUtil.getRelativeTimeString(TimeUtil.getLDTfromString(header.sendDate))
        if (header.title != "") {
            view.tvTitle.visibility = View.VISIBLE
            view.tvTitle.text = header.title
        }
        view.tvContent.text = header.context.replace("\n", "\n\n")
        view.tvComment.text = if (header.comments !=null) (header.comments.size).toString() else "0"
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
    }
}