package com.zsybh1.hobbyfriends

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.common.ImageLoader
import com.yuyh.library.imgsel.config.ISListConfig
import com.yuyh.library.imgsel.ui.ISCameraActivity.startForResult
import com.yuyh.library.imgsel.ui.ISListActivity
import com.zsybh1.hobbyfriends.Utils.BitmapUtil
import com.zsybh1.hobbyfriends.Utils.UploadUtil
import kotlinx.android.synthetic.main.activity_new_topic.*
import java.net.URI

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
}