package com.zsybh1.hobbyfriends.TopicHeader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.zsybh1.hobbyfriends.Adapter.TopicPageAdapter
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_recommend.*
import kotlinx.android.synthetic.main.fragment_time.*
import kotlin.concurrent.thread

class RecommendFragment : Fragment() {
    private lateinit var viewModel: TopicHeaderViewModel
    private lateinit var adapter: TopicPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopicHeaderViewModel::class.java)

        adapter = TopicPageAdapter(this, viewModel.dataListRecommend)
        rvRecommendHeaders.layoutManager = LinearLayoutManager(this.activity)
        rvRecommendHeaders.adapter = adapter
        refreshRecommend
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
            viewModel.getHeadersByRecommend(0)
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshRecommend.finishRefresh()
                if (viewModel.result == -1) {
                    Toast.makeText(context, "网络异常", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun onLoadMore() {
        thread {
            viewModel.getHeadersByRecommend()
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshRecommend.finishLoadMore()
                if (viewModel.result == -1) {
                    Toast.makeText(context, "网络异常", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {

        fun newInstance() = RecommendFragment()
    }
}