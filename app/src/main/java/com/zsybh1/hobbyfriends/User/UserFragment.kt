package com.zsybh1.hobbyfriends.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Adapter.DefaultViewPagerAdaper
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.layout_user_title_bar.*


class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    private val createFragment = arrayOf(
        MyInvitationFragment.newInstance(),
        MyTopicFragment.newInstance(),
        MyJoinedFragment.newInstance()
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vpHeaders.offscreenPageLimit = 1
        vpHeaders.adapter = DefaultViewPagerAdaper(requireActivity()).apply { addFragments(createFragment) }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                vpHeaders.setCurrentItem(tab!!.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })
        vpHeaders.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

    companion object {
        fun newInstance() = UserFragment()
    }
}