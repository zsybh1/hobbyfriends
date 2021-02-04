package com.zsybh1.hobbyfriends

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET)
        //验证是否许可权限
        //验证是否许可权限
        for (str in permissions) {
            if (checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                requestPermissions(permissions, 101)
            }
        }

        val navController = findNavController(R.id.nav_host)
        navBar.setupWithNavController(navController)
    }


}