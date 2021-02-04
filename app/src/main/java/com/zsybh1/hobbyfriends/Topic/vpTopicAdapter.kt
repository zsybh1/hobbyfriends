package com.zsybh1.hobbyfriends.Topic

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class vpTopicAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    val fragments = mutableListOf<Fragment>()

    override fun createFragment(position: Int): Fragment = fragments[position]

    override fun getItemCount(): Int = fragments.size

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }

    fun delFragment() {
        fragments.removeAt(1)
        notifyDataSetChanged()
    }
}