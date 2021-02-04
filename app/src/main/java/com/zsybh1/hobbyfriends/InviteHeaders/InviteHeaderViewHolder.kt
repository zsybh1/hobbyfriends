package com.zsybh1.hobbyfriends.InviteHeaders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.item_invite_header.view.*
import kotlinx.android.synthetic.main.item_joined_invitation.view.*

class InviteHeaderViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(header: IInviteModel){
        when(header) {
            is InviteHeaderModel -> {
                view.tvUsername.text =  header.username
                view.tvTime.text = TimeUtil.getRelativeTimeString(header.time)
                view.tvComment.text = header.commentNum.toString()
                view.tvContent.text = header.content?:""
                view.tvSub.text = header.subNum.toString()
                if (header.title != null) {
                    view.tvTitle.visibility = View.VISIBLE
                    view.tvTitle.text = header.title
                }
                if (header.image != null) {
                    view.imPicture.visibility = View.VISIBLE
                    BitmapUtil.display(view.imPicture, header.image)
                }
                BitmapUtil.display(view.imProfile, header.profile)
            }
            is JoinedInvitationModel -> {
                view.tvInvitationHeader.text = "活动：${header.title}"
                view.tvCountDown.text = "距开始 " + TimeUtil.getCountDownTimeString(header.time)
            }
        }
    }
}