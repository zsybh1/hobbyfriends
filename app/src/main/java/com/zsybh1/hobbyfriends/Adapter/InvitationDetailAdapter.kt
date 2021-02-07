package com.zsybh1.hobbyfriends.Adapter

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Holder.CommentViewHolder
import com.zsybh1.hobbyfriends.Holder.InvitationDetailViewHolder
import com.zsybh1.hobbyfriends.Holder.TopicDetailViewHolder
import com.zsybh1.hobbyfriends.Model.Comment
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.R

class InvitationDetailAdapter(private val fragment: Fragment, private val dataList: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, dataList[position].toString())
        when(holder) {
            is InvitationDetailViewHolder -> holder.bind(dataList[0] as Invitation, fragment)
            is CommentViewHolder -> holder.bind(dataList[position] as Comment, fragment, (dataList[0] as Invitation).id)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val root = fragment.layoutInflater.inflate(R.layout.item_invitation_detail, parent, false)
            return InvitationDetailViewHolder(root)
        }
        else {
            val root = fragment.layoutInflater.inflate(R.layout.item_comment, parent, false)
            return CommentViewHolder(root)
        }

    }
    companion object{
        private const val TAG = "InvitationDetailAdapter"
    }
}