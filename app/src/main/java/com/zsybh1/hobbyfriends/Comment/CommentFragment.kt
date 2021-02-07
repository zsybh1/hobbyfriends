package com.zsybh1.hobbyfriends.Comment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.zsybh1.hobbyfriends.Adapter.CommentDetailAdapter
import com.zsybh1.hobbyfriends.Adapter.TopicViewPagerAdapter
import com.zsybh1.hobbyfriends.Const
import com.zsybh1.hobbyfriends.Model.Comment
import com.zsybh1.hobbyfriends.Model.RequestComment
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Topic.TopicActivity
import com.zsybh1.hobbyfriends.Topic.TopicDetailFragment
import com.zsybh1.hobbyfriends.Utils.NetUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.activity_topic.*
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_comment.btnSend
import kotlinx.android.synthetic.main.fragment_comment.input
import kotlinx.android.synthetic.main.fragment_topic_detail.*
import java.time.LocalDateTime
import kotlin.concurrent.thread

class CommentFragment(private val topicId: Long, private val comment: Comment) : Fragment() {

    companion object {
        fun newInstance(topicId: Long, comment: Comment) = CommentFragment(topicId, comment)
        private const val TAG = "CommentFragment"
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

        viewModel.updateData(comment)

        CommentViewModel.selectName.observe(viewLifecycleOwner, Observer {
            input.hint = "回复 ${CommentViewModel.selectName.value}："
        })

        btnSend.setOnClickListener {
            val comment = RequestComment(
                CommentViewModel.selectId,
                requireActivity().getSharedPreferences("save", AppCompatActivity.MODE_PRIVATE).getLong("userid", 0L),
                input.text.toString(),
                TimeUtil.getStringfromLDT(LocalDateTime.now())
            )
            val json = Gson().toJson(comment)
            thread {
                val ret = NetUtil.postRequest(Const.apiHead + "/${viewModel.dataList[0].type}/${viewModel.topicId}/comment/${viewModel.dataList[0].commentId}", json)
                if (ret != null && ret[0] == '{') {
                    Log.d(TAG, "onCreate: Get json : ${ret}")
                    requireActivity().runOnUiThread{
                        Toast.makeText(context, "回复成功", Toast.LENGTH_LONG).show()
                        input.setText("")
                        ((requireActivity() as TopicActivity).vpTopic.adapter as TopicViewPagerAdapter).fragments[0].refreshTopic.autoRefresh()
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
    fun onRefresh(data: Comment? = null) {
        if (data != null) {
            viewModel.updateData(data)
        }
        adapter.notifyDataSetChanged()
        refreshComment.finishRefresh()
    }


    private fun onLoadMore(data: Comment? = null) {
        if (data != null) {
            viewModel.updateData(data)
        }
        adapter.notifyDataSetChanged()
         refreshComment.finishLoadMore()
    }

}