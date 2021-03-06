package com.zsybh1.hobbyfriends.TopicHeader

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.zsybh1.hobbyfriends.*
import com.zsybh1.hobbyfriends.Adapter.DefaultViewPagerAdaper
import kotlinx.android.synthetic.main.fragment_recommend.*
import kotlinx.android.synthetic.main.fragment_time.*
import kotlinx.android.synthetic.main.fragment_topic.*
import kotlinx.android.synthetic.main.layout_topic_title_bar.*

class TopicFragment : Fragment() {

    companion object {
        fun newInstance() = TopicFragment()
    }
    private val createFragment : Array<Fragment> = arrayOf(
        TimeFragment.newInstance(),
        RecommendFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        vpTopicHeaders.offscreenPageLimit = 1
        vpTopicHeaders.adapter = DefaultViewPagerAdaper(
            requireActivity()
        ).apply { addFragments(createFragment) }
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                vpTopicHeaders.currentItem = tab!!.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })
        vpTopicHeaders.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        btnAdd.setOnClickListener {
            val intent = Intent(this.context, NewTopicActivity::class.java)
            startActivityForResult(intent, 1234)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        (createFragment[0] as TimeFragment).refreshTime.autoRefresh()
        (createFragment[1] as RecommendFragment).refreshRecommend.autoRefresh()
    }

}