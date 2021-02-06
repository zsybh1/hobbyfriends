package com.zsybh1.hobbyfriends

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.yuyh.library.imgsel.ISNav
import com.yuyh.library.imgsel.config.ISListConfig
import com.zsybh1.hobbyfriends.Utils.UploadUtil
import kotlinx.android.synthetic.main.activity_new_topic.*
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.reflect.Array

class RegisterActivity : AppCompatActivity() {
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
            Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show()
            finish()
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