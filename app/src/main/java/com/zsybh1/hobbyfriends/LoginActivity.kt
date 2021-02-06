package com.zsybh1.hobbyfriends

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
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
            getSharedPreferences("save", Context.MODE_PRIVATE).edit { putString("username", textUsername.text.toString()) }
            Toast.makeText(this, "登录成功！", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}