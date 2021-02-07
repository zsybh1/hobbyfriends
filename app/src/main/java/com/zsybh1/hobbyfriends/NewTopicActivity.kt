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
import org.json.JSONObject
import java.time.LocalDateTime
import kotlin.concurrent.thread

class NewTopicActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "NewTopicActivity"
    }

    private val imageList = mutableListOf<String>()

    private var doing = false

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
            if (doing) {
                Toast.makeText(this, "图片上传中，请稍后", Toast.LENGTH_LONG).show()
            }
            else {
                val topic = RequestTopic(
                    ownerId = getSharedPreferences("save", MODE_PRIVATE).getLong("userid", 0L),
                    imgUrl = imageList,
                    title = textTitle.text.toString(),
                    context = textContent.text.toString(),
                    sendDate = TimeUtil.getStringfromLDT(LocalDateTime.now())
                )
                val json = Gson().toJson(topic)
                Log.d(TAG, "send json: $json")
                thread {
                    val ret = NetUtil.postRequest(Const.apiHead + "/topic", json)
                    if (ret != null && ret[0] == '{') {
                        Log.d(TAG, "get json: $ret")
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
}