package com.zsybh1.hobbyfriends.Topic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.activity_topic.*

class TopicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        val topicId = intent.getLongExtra("topicId", 0L)

        vpTopic.adapter = VpTopicAdapter(this)
        (vpTopic.adapter as VpTopicAdapter).addFragment(TopicDetailFragment.newInstance(topicId))

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && vpTopic.currentItem != 0) {
            vpTopic.setCurrentItem(0, true)
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
}