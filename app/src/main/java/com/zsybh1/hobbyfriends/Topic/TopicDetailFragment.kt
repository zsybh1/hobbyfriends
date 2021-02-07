package com.zsybh1.hobbyfriends.Topic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.zsybh1.hobbyfriends.Adapter.InvitationDetailAdapter
import com.zsybh1.hobbyfriends.Adapter.TopicDetailAdapter
import com.zsybh1.hobbyfriends.Const
import com.zsybh1.hobbyfriends.Model.RequestComment
import com.zsybh1.hobbyfriends.Model.RequestInvitation
import com.zsybh1.hobbyfriends.NewInvitationActivity
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Utils.MessageUtil
import com.zsybh1.hobbyfriends.Utils.NetUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.fragment_invite.*
import kotlinx.android.synthetic.main.fragment_topic_detail.*
import java.time.LocalDateTime
import kotlin.concurrent.thread

class TopicDetailFragment(private val topicId: Long, private val type:String) : Fragment() {

    companion object {
        fun newInstance(topicId: Long, type: String) = TopicDetailFragment(topicId, type)

        private const val TAG = "TopicDetailFragment"
    }

    lateinit var viewModel: TopicDetailViewModel
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

        btnSend.setOnClickListener {
            val comment = RequestComment(
                0,
                requireActivity().getSharedPreferences("save", AppCompatActivity.MODE_PRIVATE).getLong("userid", 0L),
                input.text.toString(),
                TimeUtil.getStringfromLDT(LocalDateTime.now())
            )
            val json = Gson().toJson(comment)
            thread {
                val ret = NetUtil.postRequest(Const.apiHead + "/${type}/${viewModel.topicId}/comment", json)
                if (ret != null && ret[0] == '{') {
                    Log.d(TAG, "onCreate: Get json : ${ret}")
                    requireActivity().runOnUiThread{
                        Toast.makeText(context, "回复成功", Toast.LENGTH_LONG).show()
                        input.setText("")
                        onRefresh()
                    }
                }
                else {
                    requireActivity().runOnUiThread{
                        Toast.makeText(context, "网络异常", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun onRefresh() {
        thread {
            viewModel.getTopic(type)
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshTopic.finishRefresh()
                MessageUtil.checkResult(viewModel.result, this)
            }
        }
    }

    private fun onLoadMore() {
        thread {
            viewModel.getTopic(type)
            requireActivity().runOnUiThread{
                adapter.notifyDataSetChanged()
                refreshTopic.finishLoadMore()
                MessageUtil.checkResult(viewModel.result, this)
            }
        }
    }
}