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
import com.zsybh1.hobbyfriends.Adapter.CommentDetailAdapter
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_invite.*
import kotlin.concurrent.thread

class CommentFragment(private val topicId: Long, private val commentId: Long) : Fragment() {

    companion object {
        fun newInstance(topicId: Long, commentId: Long) = CommentFragment(topicId, commentId)
    }

    private lateinit var viewModel: CommentViewModel
    private lateinit var adapter: CommentDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        viewModel.commentId = commentId
        viewModel.topicId = topicId

        adapter = CommentDetailAdapter(this, viewModel.dataList)
        rvComment.layoutManager = LinearLayoutManager(this.context)
        rvComment.adapter = adapter
        refreshComment
            .setRefreshHeader(MaterialHeader(this.context))
            .setRefreshFooter(BallPulseFooter(this.context))
            .setEnableLoadMore(true)
            .setEnableLoadMoreWhenContentNotFull(false)
            .setOnRefreshListener { onRefresh() }
            .setOnLoadMoreListener { onLoadMore() }

        onRefresh()
    }
    private fun onRefresh() {
        thread {
            viewModel.getComment()
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshComment.finishRefresh()
            }
        }
    }

    private fun onLoadMore() {
        thread {
            viewModel.getComment()
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshComment.finishLoadMore()
            }
        }
    }

}