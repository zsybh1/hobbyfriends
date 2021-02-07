package com.zsybh1.hobbyfriends.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Holder.MessageHeaderViewHolder
import com.zsybh1.hobbyfriends.MessageHeader.MessageHeaderModel
import com.zsybh1.hobbyfriends.R

class MessageHeaderAdapter(private val fragment: Fragment, private val headers: List<MessageHeaderModel>)
    :RecyclerView.Adapter<MessageHeaderViewHolder>(){

    override fun getItemCount(): Int = headers.size

    override fun onBindViewHolder(holder: MessageHeaderViewHolder, position: Int) {
        holder.bind(headers[position], fragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHeaderViewHolder {
        val root = fragment.layoutInflater.inflate(R.layout.item_message_header, parent, false)
        return MessageHeaderViewHolder(root)
    }
}