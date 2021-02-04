package com.zsybh1.hobbyfriends.TopicHeaders

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpDiscussAdaper(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragments = mutableListOf<Fragment>()

    fun addFragments(fragment:Array<Fragment>) {
        fragments.addAll(fragment)
    }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}