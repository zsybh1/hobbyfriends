package com.zsybh1.hobbyfriends.Topic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.zsybh1.hobbyfriends.Adapter.InvitationDetailAdapter
import com.zsybh1.hobbyfriends.Adapter.TopicDetailAdapter
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_invite.*
import kotlinx.android.synthetic.main.fragment_topic_detail.*
import kotlin.concurrent.thread

class TopicDetailFragment(private val topicId: Long, private val type:String) : Fragment() {

    companion object {
        fun newInstance(topicId: Long, type: String) = TopicDetailFragment(topicId, type)
    }

    private lateinit var viewModel: TopicDetailViewModel
    private lateinit var adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_topic_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopicDetailViewModel::class.java)
        viewModel.topicId = topicId
        if (type == "topic") {
            adapter = TopicDetailAdapter(this, viewModel.dataList)
            tvTitleBar.text = "查看主题"
        }
        else {
            adapter = InvitationDetailAdapter(this, viewModel.dataList)
            tvTitleBar.text = "查看邀请"
        }
        rvTopic.layoutManager = LinearLayoutManager(this.context)
        rvTopic.adapter = adapter
        refreshTopic
            .setRefreshHeader(MaterialHeader(this.context))
            .setRefreshFooter(BallPulseFooter(this.context))
            .setEnableLoadMore(true)
            .setEnableLoadMoreWhenContentNotFull(false)
            .setOnRefreshListener { onRefresh() }
            .setOnLoadMoreListener { onLoadMore() }
            .autoRefresh()
    }

    private fun onRefresh() {
        thread {
            viewModel.getTopic()
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshTopic.finishRefresh()
            }
        }
    }

    private fun onLoadMore() {
        thread {
            viewModel.getTopic()
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshTopic.finishLoadMore()
            }
        }
    }
}