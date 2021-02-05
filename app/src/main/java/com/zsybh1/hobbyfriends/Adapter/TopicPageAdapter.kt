package com.zsybh1.hobbyfriends.Adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Holder.TopicHeaderViewHolder
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.R

class TopicPageAdapter(private val fragment: Fragment, private val headers: List<Topic>)
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