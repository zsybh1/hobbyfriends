package com.zsybh1.hobbyfriends.MessageHeaders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.R

class MessageHeaderAdapter(private val inflater: LayoutInflater, private val headers: List<MessageHeaderModel>)
    :RecyclerView.Adapter<MessageHeaderViewHolder>(){

    override fun getItemCount(): Int = headers.size

    override fun onBindViewHolder(holder: MessageHeaderViewHolder, position: Int) {
        holder.bind(headers[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHeaderViewHolder {
        val root = inflater.inflate(R.layout.item_message_header, parent, false)
        return MessageHeaderViewHolder(root)
    }
}