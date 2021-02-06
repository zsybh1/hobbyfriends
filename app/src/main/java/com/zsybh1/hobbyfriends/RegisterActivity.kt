package com.zsybh1.hobbyfriends

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import com.zsybh1.hobbyfriends.Model.Topic
import com.zsybh1.hobbyfriends.Model.User
import com.zsybh1.hobbyfriends.Utils.NetUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.textUsername
import org.json.JSONObject
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val config=ISListConfig.Builder()
            .multiSelect(false).build()
        ISNav.getInstance().init { context, path, imageView ->  Glide.with(context).load(path).into(imageView)}

        layoutGender.setOnClickListener {
            val chooseList = arrayOf("男","女","保密")
            AlertDialog.Builder(this)
                .setTitle("性别")
                .setSingleChoiceItems(chooseList, chooseList.indexOf(tvGender.text), DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
                    val item = chooseList[i]
                    tvGender.text = item
                    dialogInterface.dismiss()
                }).show()
        }
        tvChooseHeadImg.setOnClickListener {
            ISNav.getInstance().toListActivity(this, config, 12345)
        }
        btnRegister.setOnClickListener {
            if (textUsername.text.toString().length < 6 || textUsername.text.toString().length > 18) {
                Toast.makeText(this, "用户名长度需在6-18之间", Toast.LENGTH_LONG).show()
            }
            else if (textPassword.text.toString() != textCheckPwd.text.toString()) {
                Toast.makeText(this, "用户名长度需在6-18之间", Toast.LENGTH_LONG).show()
            }
            else {
                val user = User(
                    0,
                    textUsername.text.toString(),
                    textPassword.text.toString(),
                    tvGender.text.toString(),
                    textMail.text.toString(),
                    Const.apiHead + "/images/head/1.png"
                )
                val json = Gson().toJson(user)
                Log.d(TAG, json)
                thread {
                    val ret = NetUtil.postRequest(Const.apiHead + "/user",json)
                    if (ret != null && ret[0] == '{') {
                        val response = JSONObject(ret)
                        val dataJson = response.getJSONObject("data")
                        val data = Gson().fromJson(dataJson.toString(), User::class.java)
                        Log.d(TAG, "注册获得id: ${data.id}")
                        //TODO: 在登录界面获得并存放用户id
                        getSharedPreferences("save", Context.MODE_PRIVATE).edit { putLong("userid", data.id) }
                        runOnUiThread{
                            Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show()
                        }
                    }
                    finish()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 12345 && resultCode == RESULT_OK && data != null){
            val path= data.getStringArrayListExtra("result")
            Toast.makeText(this, "已选择${path!![0]}", Toast.LENGTH_LONG).show()
        }
    }
}