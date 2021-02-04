package com.zsybh1.hobbyfriends.InviteHeaders

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.R

class InviteHeaderAdapter(private val inflater: LayoutInflater, private val headers : List<IInviteModel>)
    :RecyclerView.Adapter<InviteHeaderViewHolder>(){
    override fun getItemCount(): Int = headers.size

    override fun onBindViewHolder(holder: InviteHeaderViewHolder, position: Int) {
        holder.bind(headers[position])
    }

    override fun getItemViewType(position: Int): Int {
        when(headers[position]) {
            is InviteHeaderModel -> return NORMAL
            is JoinedInvitationModel -> return JOINED
            else -> {
                Log.d(TAG, "getItemViewType: Get wrong item view type.")
                return ERROR
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteHeaderViewHolder {
        val root = when(viewType) {
            JOINED -> inflater.inflate(R.layout.item_joined_invitation, parent, false)
            NORMAL -> inflater.inflate(R.layout.item_invite_header, parent, false)
            else -> null
        }
        return InviteHeaderViewHolder(root!!)
    }

    companion object {
        private const val TAG = "InviteHeaderAdapter"
        const val ERROR = -1
        const val JOINED = 0
        const val NORMAL = 1
    }
}