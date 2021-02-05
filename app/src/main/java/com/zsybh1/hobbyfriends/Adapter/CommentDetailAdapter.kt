package com.zsybh1.hobbyfriends.Adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Holder.CommentDetailViewHolder
import com.zsybh1.hobbyfriends.Model.Comment
import com.zsybh1.hobbyfriends.R

class CommentDetailAdapter(private val fragment: Fragment, private val comment: Comment) : RecyclerView.Adapter<CommentDetailViewHolder>() {
    override fun getItemCount(): Int = comment.subComments.size + 1

    override fun onBindViewHolder(holder: CommentDetailViewHolder, position: Int) {
        holder.bind(if (position == 0) comment else comment.subComments[position - 1])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentDetailViewHolder {
        val root = fragment.layoutInflater.inflate(R.layout.item_comment, parent, false)
        return CommentDetailViewHolder(root)
    }
}