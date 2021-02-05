package com.zsybh1.hobbyfriends.User

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Adapter.TopicPageAdapter
import kotlinx.android.synthetic.main.fragment_my_topic.*

class MyTopicFragment : Fragment() {

    companion object {
        fun newInstance() = MyTopicFragment()
    }

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_topic, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        rvMyTopic.layoutManager = LinearLayoutManager(this.context)
        rvMyTopic.adapter = TopicPageAdapter(this, viewModel.getTopic(123)) //TODO: 获得自己的用户id
    }

}