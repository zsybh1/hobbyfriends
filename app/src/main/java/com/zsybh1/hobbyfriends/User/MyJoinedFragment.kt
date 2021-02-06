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
import kotlinx.android.synthetic.main.fragment_my_joined.*
import kotlinx.android.synthetic.main.fragment_time.*
import kotlin.concurrent.thread

class MyJoinedFragment : Fragment() {

    companion object {
        fun newInstance() = MyJoinedFragment()
    }

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: InvitePageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_joined, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val userId = requireActivity().getSharedPreferences("save", Context.MODE_PRIVATE).getLong("userid", 0L)
        adapter = InvitePageAdapter(this, viewModel.dataListJoined)
        rvMyJoined.layoutManager = LinearLayoutManager(this.activity)
        rvMyJoined.adapter = adapter
        refreshMyJoined
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
            viewModel.getJoined(userId, 0)
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshMyJoined.finishRefresh()
                if (viewModel.result == -1) {
                    Toast.makeText(context, "网络异常", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun onLoadMore(userId:Long) {
        thread {
            viewModel.getJoined(userId)
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshMyJoined.finishLoadMore()
                if (viewModel.result == -1) {
                    Toast.makeText(context, "网络异常", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}