package com.zsybh1.hobbyfriends.Topic

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.R

class TopicDetailAdapter(private val fragment: Fragment, private val topic: Topic) : RecyclerView.Adapter<TopicDetailViewHolder>(){
    override fun getItemCount(): Int = topic.comments.size + 1

    override fun onBindViewHolder(holder: TopicDetailViewHolder, position: Int) {
        holder.bind(if (position == 0) topic else topic.comments[position-1])
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return  TOPIC
        }
        else if(position >= 1) {
            return COMMENT
        }
        else {
            Log.d(TAG, "getItemViewType: Get wrong item view type")
            return  ERROR
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicDetailViewHolder {
        val resource = when(viewType) {
            TOPIC -> R.layout.item_topic_detail
            COMMENT -> R.layout.item_comment
            else -> null
        }
        val root = fragment.layoutInflater.inflate(resource!!, parent, false)
        return TopicDetailViewHolder(fragment, root, topic.id)
    }

    companion object {
        private const val TAG = "TopicDetailAdapter"
        const val ERROR = -1
        const val TOPIC = 0
        const val COMMENT = 1
    }
}