package com.zsybh1.hobbyfriends.TopicHeaders

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.MainActivity
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Topic.TopicActivity

class TopicHeaderAdapter(private val fragment: Fragment, private val headers: List<TopicHeaderModel>)
    : RecyclerView.Adapter<TopicHeaderViewHolder>() {

    override fun getItemCount(): Int = headers.size

    override fun onBindViewHolder(holder: TopicHeaderViewHolder, position: Int) {
        holder.bind(headers[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicHeaderViewHolder {
        val root = fragment.layoutInflater.inflate(R.layout.item_topic_header, parent, false)
        return TopicHeaderViewHolder(fragment, root)
    }
}