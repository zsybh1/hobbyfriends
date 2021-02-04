package com.zsybh1.hobbyfriends.InviteHeaders

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.zsybh1.hobbyfriends.NewInvitationActivity
import com.zsybh1.hobbyfriends.R
import kotlinx.android.synthetic.main.fragment_invite.*

class InviteFragment : Fragment() {

    companion object {
        fun newInstance() = InviteFragment()
    }

    private lateinit var viewModel: InviteHeaderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_invite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InviteHeaderViewModel::class.java)

        rvInvite.layoutManager = LinearLayoutManager(this.activity)
        rvInvite.adapter = InviteHeaderAdapter(layoutInflater, viewModel.getHeaders())

        btnAdd.setOnClickListener {
            val intent = Intent(this.context, NewInvitationActivity::class.java)
            startActivity(intent)
        }
    }

}