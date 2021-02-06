package com.zsybh1.hobbyfriends

import android.app.VoiceInteractor
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import com.zsybh1.hobbyfriends.Model.Comment
import com.zsybh1.hobbyfriends.Model.RequestTopic
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Utils.NetUtil
import com.zsybh1.hobbyfriends.Utils.TimeUtil
import kotlinx.android.synthetic.main.activity_new_topic.*
import java.time.LocalDateTime
import kotlin.concurrent.thread

class NewTopicActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "NewTopicActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_topic)

        val config = ISListConfig.Builder()
            .maxNum(9)
            .build()
        ISNav.getInstance().init { context, path, imageView ->  Glide.with(context).load(path).into(imageView)}
        tvChoose.setOnClickListener {
            ISNav.getInstance().toListActivity(this, config, 1234)
        }

        tvPush.setOnClickListener {
            val topic = RequestTopic(
                ownerId = getSharedPreferences("save", MODE_PRIVATE).getLong("userid", 0L),
                imgUrl = listOf("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1824156972,226889516&fm=26&gp=0.jpg","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170713%2Facdf59999adf4f4793086cace080d236.png&refer=http%3A%2F%2Fimg.mp.sohu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1615184219&t=9ba8f16808c3f716c419edd15150ccf4"),
                title = textTitle.text.toString(),
                context = textContent.text.toString(),
                sendDate = TimeUtil.getStringfromLDT(LocalDateTime.now())
            )
            val json = Gson().toJson(topic)
            Log.d(TAG, "send json: $json")
            thread {
                val ret = NetUtil.postRequest(Const.apiHead + "/topic", json)
                if (ret != null && ret[0] == '{'){
                    Log.d(TAG, "get json: $ret")
                    runOnUiThread { finish() }
                }
                else {
                    runOnUiThread{
                        Toast.makeText(this, "网络异常", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == RESULT_OK && data != null){
            val pathList = data.getStringArrayListExtra("result")
            for (path in pathList!!) {
                NetUtil.upload(path)
            }
            tvCount.text = "已选择${pathList.size}张"
        }
    }
}