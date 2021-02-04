package com.zsybh1.hobbyfriends.Topic

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.R

class CommentAdapter(private val fragment: Fragment, private val comment: Comment) : RecyclerView.Adapter<CommentViewHolder>() {
    override fun getItemCount(): Int = comment.subComments.size + 1

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(if (position == 0) comment else comment.subComments[position - 1])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val root = fragment.layoutInflater.inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(fragment, root)
    }
}