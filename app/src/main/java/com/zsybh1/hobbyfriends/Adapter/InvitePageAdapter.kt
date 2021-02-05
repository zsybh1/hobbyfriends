package com.zsybh1.hobbyfriends.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Holder.InviteHeaderViewHolder
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.R

class InvitePageAdapter(private val inflater: LayoutInflater, private val headers : List<Invitation>)
    :RecyclerView.Adapter<InviteHeaderViewHolder>(){
    override fun getItemCount(): Int = headers.size

    override fun onBindViewHolder(holder: InviteHeaderViewHolder, position: Int) {

        holder.bind(headers[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteHeaderViewHolder {
        val root = inflater.inflate(R.layout.item_invite_header, parent, false)
        return InviteHeaderViewHolder(root)
    }

    companion object {
        private const val TAG = "InviteHeaderAdapter"
    }
}