package com.zsybh1.hobbyfriends.Topic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_topic_detail.*

class TopicDetailFragment(private val topicId: Long) : Fragment() {

    companion object {
        fun newInstance(topicId: Long) = TopicDetailFragment(topicId)
    }

    private lateinit var viewModel: TopicDetailViewModel

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

        rvTopic.layoutManager = LinearLayoutManager(this.context)
        rvTopic.adapter = TopicDetailAdapter(this, viewModel.getTopic())
    }

}