package com.zsybh1.hobbyfriends.Utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.zsybh1.hobbyfriends.LoginActivity

object MessageUtil {
    fun checkResult(result: Int, fragment: Fragment) {
        if (result == -100) {
            fragment.requireActivity().getSharedPreferences("save", Context.MODE_PRIVATE).edit {
                putLong("userid", 0)
                putString("token", "")
            }
            Toast.makeText(fragment.context, "请重新登录", Toast.LENGTH_LONG).show()
            fragment.startActivity(Intent(fragment.context, LoginActivity::class.java))
            fragment.requireActivity().finish()
        }
        if (result == -1) {
            Toast.makeText(fragment.context, "网络异常", Toast.LENGTH_LONG).show()
        }
    }
}