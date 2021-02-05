package com.zsybh1.hobbyfriends.Holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Model.Activity
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_joined_invitation.view.*

class ActivityViewHolder(private val view: View) :RecyclerView.ViewHolder(view) {
    fun bind(data: Invitation) {
        view.tvInvitationHeader.text = data.title
        view.tvCountDown.text = TimeUtil.getCountDownTimeString(TimeUtil.getLDTfromString(data.activity.start))
    }
}