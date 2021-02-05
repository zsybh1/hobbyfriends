package com.zsybh1.hobbyfriends.TopicHeader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.zsybh1.hobbyfriends.Adapter.TopicPageAdapter
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_time.*
import kotlin.concurrent.thread

class TimeFragment : Fragment() {

    private lateinit var viewModel: TopicHeaderViewModel
    private lateinit var adapter: TopicPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_time, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopicHeaderViewModel::class.java)

        adapter = TopicPageAdapter(this, viewModel.dataListTime)
        rvTimeHeaders.layoutManager =LinearLayoutManager(this.activity)
        rvTimeHeaders.adapter = adapter
        refreshTime
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
            viewModel.getHeadersByTime(0)
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshTime.finishRefresh()
            }
        }
    }

    private fun onLoadMore() {
        thread {
            viewModel.getHeadersByTime()
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshTime.finishLoadMore()
            }
        }
    }
    companion object {

        fun newInstance() = TimeFragment()
    }
}