package com.zsybh1.hobbyfriends.Topic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.activity_topic.*

class TopicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        val topicId = intent.getLongExtra("topicId", 0L)

        vpTopic.adapter = vpTopicAdapter(this)
        (vpTopic.adapter as vpTopicAdapter).addFragment(TopicDetailFragment.newInstance(topicId))

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && vpTopic.currentItem != 0) {
            vpTopic.setCurrentItem(0, true)
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
}