package com.zsybh1.hobbyfriends

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import com.zsybh1.hobbyfriends.Model.Activity
import com.zsybh1.hobbyfriends.Model.Invitation
import com.zsybh1.hobbyfriends.Model.RequestInvitation
import com.zsybh1.hobbyfriends.Model.User
import com.zsybh1.hobbyfriends.Utils.NetUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.activity_new_invitation.*
import kotlinx.android.synthetic.main.activity_new_invitation.textContent
import kotlinx.android.synthetic.main.activity_new_invitation.textTitle
import kotlinx.android.synthetic.main.activity_new_invitation.tvChoose
import kotlinx.android.synthetic.main.activity_new_invitation.tvCount
import kotlinx.android.synthetic.main.activity_new_invitation.tvDeadline
import kotlinx.android.synthetic.main.activity_new_invitation.tvEndTime
import kotlinx.android.synthetic.main.activity_new_invitation.tvPush
import kotlinx.android.synthetic.main.activity_new_invitation.tvStartTime
import kotlinx.android.synthetic.main.activity_new_topic.*
import kotlinx.android.synthetic.main.item_invitation_detail.*
import org.json.JSONObject
import java.time.LocalDateTime
import java.util.*
import kotlin.concurrent.thread

class NewInvitationActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "NewInvitationActivity"
    }
    private val imageList = mutableListOf<String>()

    private var doing = false
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
            getTimeString(tvDeadline, "参加截止时间: ")
        }
        tvStartTime.setOnClickListener {
            getTimeString(tvStartTime, "活动开始时间: ")
        }
        tvEndTime.setOnClickListener {
            getTimeString(tvEndTime, "活动结束时间: ")
        }

        tvPush.setOnClickListener {
            if (doing) {
                Toast.makeText(this, "图片上传中，请稍后", Toast.LENGTH_LONG).show()
            }
            else {
                val invitation = RequestInvitation(
                    ownerId = getSharedPreferences("save", MODE_PRIVATE).getLong("userid", 0L),
                    imgUrl = imageList,
                    tag = "",
                    title = textTitle.text.toString(),
                    context = textContent.text.toString(),
                    sendDate = TimeUtil.getStringfromLDT(LocalDateTime.now()),
                    activity = Activity(
                        tag = textTag.text.toString(),
                        deadline = tvDeadline.text.substring(tvDeadline.text.indexOf(" ") + 1) + ":00",
                        start = tvStartTime.text.substring(tvStartTime.text.indexOf(" ") + 1) + ":00",
                        end = tvEndTime.text.substring(tvEndTime.text.indexOf(" ") + 1) + ":00",
                        position = textPlace.text.toString(),
                        followCount = textFollowCount.text.toString().toInt(),
                        followers = listOf()
                    )
                )
                val json = Gson().toJson(invitation)
                Log.d(TAG, "Send json : ${json}")
                thread {
                    val ret = NetUtil.postRequest(Const.apiHead + "/invitation", json)
                    if (ret != null && ret[0] == '{') {
                        Log.d(TAG, "onCreate: Get json : ${ret}")
                        runOnUiThread { finish() }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this, "网络异常", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == RESULT_OK && data != null){
            val pathList = data.getStringArrayListExtra("result")
            thread {
                doing = true
                var success = true
                for (path in pathList!!) {
                    val ret = NetUtil.upload(Const.apiHead + "/img/upload",path)
                    Log.d(TAG, "onActivityResult: get json ${ret}")
                    if (ret != null && ret[0] == '{') {
                        val url = JSONObject(ret).getString("url")
                        Log.d(TAG, "onActivityResult: get url ${url}")
                        imageList.add(Const.apiHead + url)
                    }
                    else {
                        runOnUiThread{
                            success = false
                            Toast.makeText(this, "上传失败", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                if (success) {
                    runOnUiThread{
                        Toast.makeText(this, "上传成功", Toast.LENGTH_LONG).show()
                    }
                }
                doing = false
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
                    time.append(String.format(" %04d-%02d-%02d", year, month + 1, dayOfMonth))
                    timePickerDialog.show()
                }, year, month, day)

        datePickerDialog.show()
    }
}