package com.zsybh1.hobbyfriends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_complete.*

class CompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        val userid = intent.getLongExtra("userid", 0L)
        tvUserId.text = userid.toString()

        btnBack.setOnClickListener {
            finish()
        }
    }
}