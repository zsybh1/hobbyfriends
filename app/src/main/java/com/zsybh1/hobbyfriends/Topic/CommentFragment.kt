package com.zsybh1.hobbyfriends.Topic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_comment.*

class CommentFragment(private val topicId: Long, private val commentId: Long) : Fragment() {

    companion object {
        fun newInstance(topicId: Long, commentId: Long) = CommentFragment(topicId, commentId)
    }

    private lateinit var viewModel: CommentViewModel

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

        rvComment.layoutManager = LinearLayoutManager(this.context)
        rvComment.adapter = CommentAdapter(this, viewModel.getComment())
    }

}