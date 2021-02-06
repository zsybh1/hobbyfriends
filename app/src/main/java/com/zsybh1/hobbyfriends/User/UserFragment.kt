package com.zsybh1.hobbyfriends.User

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.zsybh1.hobbyfriends.R
import com.zsybh1.hobbyfriends.Adapter.DefaultViewPagerAdaper
import com.zsybh1.hobbyfriends.LoginActivity
import com.zsybh1.hobbyfriends.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.layout_user_title_bar.*


class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
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

        imSetting.setOnClickListener {
            val menu = PopupMenu(context, imSetting)
            menu.menuInflater.inflate(R.menu.menu_me, menu.menu)
            menu.setOnMenuItemClickListener{item ->
                when (item.itemId) {
                    R.id.menuLogout -> {
                        requireActivity().getSharedPreferences("save", Context.MODE_PRIVATE).edit { putLong("userid", 0) }
                        Toast.makeText(context, "退出登录", Toast.LENGTH_LONG).show()
                        startActivity(Intent(context, LoginActivity::class.java))
                        requireActivity().finish()
                    }
                }
                true
            }
            menu.show()
        }
    }

    companion object {
        fun newInstance() = UserFragment()
    }
}