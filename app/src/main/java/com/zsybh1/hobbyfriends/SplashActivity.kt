package com.zsybh1.hobbyfriends

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zsybh1.hobbyfriends.Utils.NetUtil
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET)
        //验证是否许可权限
        //验证是否许可权限
        for (str in permissions) {
            if (checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                requestPermissions(permissions, 101)
            }
        }
        NetUtil.token =
            getSharedPreferences("save", Context.MODE_PRIVATE).getString("token", "").toString()
        if (NetUtil.token == "null") {
            NetUtil.token = ""
        }
        CoroutineScope(Job()).launch {
            delay(2000)
            if (NetUtil.token == ""){
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            else {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
            finish()
        }

    }


}