package com.zsybh1.hobbyfriends.Holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Const
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.NetUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_topic_detail.view.*
import kotlin.concurrent.thread

class TopicDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(data: Topic, fragment: Fragment) {
        view.tvTitle.visibility = View.GONE
        view.imageLayout.visibility = View.GONE
        view.imageLayout.removeAllViews()
        view.imProfile.setImageResource(R.mipmap.default_image)
        view.tvContent.text = data.context

        if (data.headImg != null) {
            BitmapUtil.display(view.imProfile, data.headImg)
        }
        view.tvUsername.text = data.username
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
        view.tvTime.text = TimeUtil.getRelativeTimeString(TimeUtil.getLDTfromString(data.sendDate))
        if (data.title != "") {
            view.tvTitle.visibility = View.VISIBLE
            view.tvTitle.text = data.title
        }
        view.tvComment.text = if (data.comments !=null) (data.comments.size).toString() else "0"
        view.tvSub.text = (data.likes?:0).toString()
        if (data.imgUrl.isNotEmpty()) {
            view.imageLayout.visibility = View.VISIBLE
            for (url in data.imgUrl) {
                val imageView = ImageView(view.context)
                val dm = view.resources.displayMetrics
                val width = dm.widthPixels
                val density = dm.density
                BitmapUtil.display(imageView, url, (width - 16 * density).toInt())
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                view.imageLayout.addView(imageView)
            }
        }
    }
}