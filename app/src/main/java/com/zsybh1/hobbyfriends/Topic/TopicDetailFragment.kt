package com.zsybh1.hobbyfriends.Topic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.zsybh1.hobbyfriends.Adapter.TopicDetailAdapter
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_invite.*
import kotlinx.android.synthetic.main.fragment_topic_detail.*
import kotlin.concurrent.thread

class TopicDetailFragment(private val topicId: Long) : Fragment() {

    companion object {
        fun newInstance(topicId: Long) = TopicDetailFragment(topicId)
    }

    private lateinit var viewModel: TopicDetailViewModel
    private lateinit var adapter : TopicDetailAdapter

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

        adapter = TopicDetailAdapter(this, viewModel.dataList)
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