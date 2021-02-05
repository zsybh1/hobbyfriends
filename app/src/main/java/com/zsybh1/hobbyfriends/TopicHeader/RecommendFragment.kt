package com.zsybh1.hobbyfriends.TopicHeader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zsybh1.hobbyfriends.Adapter.TopicPageAdapter
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_recommend.*

class RecommendFragment : Fragment() {
    private lateinit var viewModel: TopicHeaderViewModel

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

        rvRecommendHeaders.layoutManager = LinearLayoutManager(this.activity)
        rvRecommendHeaders.adapter = TopicPageAdapter(this, viewModel.getHeadersByRecommend())
    }
    companion object {

        fun newInstance() = RecommendFragment()
    }
}