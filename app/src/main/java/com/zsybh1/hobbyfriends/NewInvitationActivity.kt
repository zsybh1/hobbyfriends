package com.zsybh1.hobbyfriends

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import com.zsybh1.hobbyfriends.Utils.UploadUtil
import kotlinx.android.synthetic.main.activity_new_invitation.*
import kotlinx.android.synthetic.main.activity_new_invitation.tvChoose
import kotlinx.android.synthetic.main.activity_new_invitation.tvCount
import kotlinx.android.synthetic.main.activity_new_topic.*
import java.text.Format
import java.util.*

class NewInvitationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_invitation)

        val config = ISListConfig.Builder()
                .maxNum(9)
                .build()
        ISNav.getInstance().init { context, path, imageView ->  Glide.with(context).load(path).into(imageView)}
        tvChoose.setOnClickListener {
            ISNav.getInstance().toListActivity(this, config, 1234)
        }
        tvDeadline.setOnClickListener {
            getTimeString(tvDeadline, "参加截止时间：")
        }
        tvStartTime.setOnClickListener {
            getTimeString(tvStartTime, "活动开始时间：")
        }
        tvEndTime.setOnClickListener {
            getTimeString(tvEndTime, "活动结束时间：")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == RESULT_OK && data != null){
            val pathList = data.getStringArrayListExtra("result")
            for (path in pathList!!) {
                UploadUtil.upload(path)
            }
            tvCount.text = "已选择${pathList.size}张"
        }
    }

    fun getTimeString(textView: TextView, preString:String){
        val time = StringBuffer(preString)
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val hour = calender.get(Calendar.HOUR_OF_DAY)
        val minute = calender.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this,
                { view, hourOfDay, minute ->
                    time.append(String.format(" %02d:%02d", hourOfDay, minute))
                    textView.text = time.toString()
                }, hour,minute,true)
        val datePickerDialog = DatePickerDialog(this,
                { view, year, month, dayOfMonth ->
                    time.append(String.format(" %04d-%02d-%02d", year, month, dayOfMonth))
                    timePickerDialog.show()
                }, year, month, day)

        datePickerDialog.show()
    }
}