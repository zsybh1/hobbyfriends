package com.zsybh1.hobbyfriends.User

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.zsybh1.hobbyfriends.Adapter.InvitePageAdapter
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Utils.MessageUtil
import kotlinx.android.synthetic.main.fragment_my_invitation.*
import kotlinx.android.synthetic.main.fragment_time.*
import kotlin.concurrent.thread

class MyInvitationFragment : Fragment() {

    companion object {
        fun newInstance() = MyInvitationFragment()
    }

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: InvitePageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_invitation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val userId = requireActivity().getSharedPreferences("save", Context.MODE_PRIVATE).getLong("userid", 0L)
        adapter = InvitePageAdapter(this, viewModel.dataListInvitation)
        rvMyInvitation.layoutManager = LinearLayoutManager(this.activity)
        rvMyInvitation.adapter =  adapter
        refreshMyInvitation
            .setRefreshHeader(MaterialHeader(this.context))
            .setRefreshFooter(BallPulseFooter(this.context))
            .setEnableLoadMore(true)
            .setEnableLoadMoreWhenContentNotFull(false)
            .setOnRefreshListener { onRefresh(userId) }
            .setOnLoadMoreListener { onLoadMore(userId) }
            .autoRefresh()

    }
    private fun onRefresh(userId:Long) {
        thread {
            viewModel.getInvitation(userId, 0)
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshMyInvitation.finishRefresh()
                MessageUtil.checkResult(viewModel.result, this)
            }
        }
    }

    private fun onLoadMore(userId: Long) {
        thread {
            viewModel.getInvitation(userId)
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshMyInvitation.finishLoadMore()
                MessageUtil.checkResult(viewModel.result, this)
            }
        }
    }
}