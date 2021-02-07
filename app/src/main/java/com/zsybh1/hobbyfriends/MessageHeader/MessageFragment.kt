package com.zsybh1.hobbyfriends.MessageHeader

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.zsybh1.hobbyfriends.Adapter.MessageHeaderAdapter
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : Fragment() {

    companion object {
        fun newInstance() = MessageFragment()
    }

    private lateinit var viewModel: MessageHeaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MessageHeaderViewModel::class.java)

        rvMessageHeaders.layoutManager = LinearLayoutManager(this.activity)
        rvMessageHeaders.adapter = MessageHeaderAdapter(this, viewModel.getHeaders())
    }

}