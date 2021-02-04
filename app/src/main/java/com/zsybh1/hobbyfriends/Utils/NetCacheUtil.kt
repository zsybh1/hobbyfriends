package com.zsybh1.hobbyfriends.Utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class NetCacheUtil {

    fun getBitmap(ivPic: ImageView, url: String){
        Log.d(TAG, "getBitmap: start")
        DownloadTask().execute(ivPic, url)
    }

    private inner class DownloadTask : AsyncTask<Any,Void,Bitmap>(){
        lateinit var ivPic: ImageView
        lateinit var url: String
        // 传入的参数：ivPic, url
        override fun doInBackground(vararg params: Any?): Bitmap? {
            ivPic = params[0] as ImageView
            url = params[1] as String
            val bitmap = downloadBitmap(url)
//            ivPic.tag = ivPic   // 标记imageView使得imageView和url可以一一对应

            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            ivPic.setImageBitmap(result)
            BitmapUtil.localCacheUtil.saveBitmap(url, result)
            BitmapUtil.memoryCacheUtil.saveBitmap(url, result)
        }
    }

    // 根据url下载图片
    private fun downloadBitmap(url:String): Bitmap?{
        lateinit var conn: HttpURLConnection
        try {
            val mUrl = URL(url)
            conn = mUrl.openConnection() as HttpURLConnection

            conn.connectTimeout = 5000
            conn.readTimeout = 5000
            conn.requestMethod = "GET"

            conn.connect()

            val code = conn.responseCode
            if (code == 200) {

                val input = conn.inputStream
                val bitmap = BitmapFactory.decodeStream(input)
                Log.d(TAG, "downloadBitmap: Succeed")
                return bitmap
            }
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            conn.disconnect()
        }

        return null
    }

    companion object {
        private const val TAG = "NetCacheUtil"
    }

}