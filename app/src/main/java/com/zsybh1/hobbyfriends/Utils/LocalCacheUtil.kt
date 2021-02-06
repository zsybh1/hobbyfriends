package com.zsybh1.hobbyfriends.Utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.zsybh1.hobbyfriends.MainActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class LocalCacheUtil {
    companion object{
        val FILE_PATH = Environment.getExternalStorageDirectory().absolutePath + File.separator + "images" + File.separator
    }

    fun getBitmap(url: String) : Bitmap?{
        val fileName = MD5Util.encode(url)
        val file = File(FILE_PATH, fileName)
        if (file.exists()) {
            val bitmap = BitmapFactory.decodeStream(FileInputStream(file))
            BitmapUtil.memoryCacheUtil.saveBitmap(url, bitmap)
            return bitmap
        }

        return null
    }

    // 将图片保存在本地
    fun saveBitmap(url: String, bitmap: Bitmap?){
        val fileName = MD5Util.encode(url)
        val file = File(FILE_PATH, fileName)
        if (!file.parentFile!!.exists()){
            file.parentFile!!.mkdirs()
        }
        val type = url.substring(url.lastIndexOf('.'))
        if (type.equals("png", true)){
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream(file))
        }
        else {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))
        }
    }
}