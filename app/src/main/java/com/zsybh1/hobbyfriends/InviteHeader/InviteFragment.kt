package com.zsybh1.hobbyfriends.InviteHeader

import android.content.Intent
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
import com.zsybh1.hobbyfriends.NewInvitationActivity
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Utils.MessageUtil
import kotlinx.android.synthetic.main.fragment_invite.*
import kotlin.concurrent.thread

class InviteFragment : Fragment() {

    companion object {
        fun newInstance() = InviteFragment()
        private const val TAG = "InviteFragment"
    }

    private lateinit var viewModel: InviteHeaderViewModel
    private lateinit var adapter: InvitePageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InviteHeaderViewModel::class.java)


        adapter = InvitePageAdapter(this, viewModel.dataList)
        rvInvite.layoutManager = LinearLayoutManager(this.activity)
        rvInvite.adapter = adapter
        refreshInvite.setRefreshHeader(MaterialHeader(this.context))
        refreshInvite.setRefreshFooter(BallPulseFooter(this.context))
        refreshInvite.setEnableLoadMore(true)
        refreshInvite.setEnableLoadMoreWhenContentNotFull(false)
        refreshInvite.setOnRefreshListener { onRefresh() }
        refreshInvite.setOnLoadMoreListener { onLoadMore() }

        refreshInvite.autoRefresh()


        btnAdd.setOnClickListener {
            val intent = Intent(this.context, NewInvitationActivity::class.java)
            startActivityForResult(intent, 1234)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        refreshInvite.autoRefresh()
    }

    private fun onRefresh() {
        thread {
            viewModel.getPage(0)
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshInvite.finishRefresh()
                MessageUtil.checkResult(viewModel.result, this)
            }
        }
    }

    private fun onLoadMore() {
        thread {
            viewModel.getPage()
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshInvite.finishLoadMore()
                MessageUtil.checkResult(viewModel.result, this)
            }
        }
    }


}