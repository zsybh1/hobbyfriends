package com.zsybh1.hobbyfriends.Topic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.zsybh1.hobbyfriends.Adapter.TopicViewPagerAdapter
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.activity_topic.*

class TopicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        val topicId = intent.getLongExtra("topicId", 0L)
        val type = intent.getStringExtra("type")

        vpTopic.adapter = TopicViewPagerAdapter(this)
        (vpTopic.adapter as TopicViewPagerAdapter).fragments.add(TopicDetailFragment.newInstance(topicId, type))

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && vpTopic.currentItem != 0) {
            vpTopic.setCurrentItem(0, true)
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
}