package com.zsybh1.hobbyfriends

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import com.google.gson.Gson
import com.zsybh1.hobbyfriends.Model.User
import com.zsybh1.hobbyfriends.Utils.NetUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.textUsername
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvChangePwd.setOnClickListener {
            Toast.makeText(this, "该功能尚未完成！", Toast.LENGTH_LONG).show()
        }
        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        btnLogin.setOnClickListener {
            val user = User(
                id = if(textUsername.text.toString() != "") textUsername.text.toString().toLong() else 0,
                username = "",
                pwd = TextPassword.text.toString(),
                gender = "",
                email = "",
                headImgUrl = ""
            )
            val json = Gson().toJson(user)
            Log.d(TAG, "onCreate: send json ${json}")
            thread {
                val ret = NetUtil.postRequest(Const.apiHead + "/login",json)
                if (ret != null && ret[0] == '{') {
                    val response = JSONObject(ret)
                    val back = response.optString("data")
                    Log.d(TAG, "onCreate: get response ${back}")
                    if (back == "No value present") {
                        runOnUiThread {
                            Toast.makeText(this, "用户不存在", Toast.LENGTH_LONG).show()
                        }
                    }
                    else if (back == "null") {
                        runOnUiThread {
                            Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show()
                        }
                    }
                    else {
                        NetUtil.token = back
                        getSharedPreferences("save", Context.MODE_PRIVATE).edit {
                            putLong("userid", user.id)
                            putString("token", back)
                        }
                        runOnUiThread {
                            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                }
                else if (ret != null){
                    runOnUiThread {
                        Toast.makeText(this, "用户不存在或密码错误", Toast.LENGTH_LONG).show()
                    }
                }
                else {
                    runOnUiThread {
                        Toast.makeText(this, "网络异常", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}