package com.zsybh1.hobbyfriends.TopicHeaders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_time.*

class TimeFragment : Fragment() {

    private lateinit var viewModel: TopicHeaderViewModel

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

        rvTimeHeaders.layoutManager =LinearLayoutManager(this.activity)
        rvTimeHeaders.adapter = TopicHeaderAdapter(this, viewModel.getHeadersByTime())
    }

    companion object {

        fun newInstance() = TimeFragment()
    }
}