package com.zsybh1.hobbyfriends.Utils

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.zsybh1.hobbyfriends.R

object BitmapUtil {
    private val netCacheUtil = NetCacheUtil()
    val localCacheUtil = LocalCacheUtil()
    val memoryCacheUtil = MemoryCacheUtil()

    fun display(ivPic: ImageView, url: String?, reshapeWidth :Int = -1) {
        if (url == null) {
            ivPic.setImageResource(R.mipmap.default_image)
            return
        }

        // 内存缓存
        var bitmap = memoryCacheUtil.getBitmap(url)
        if (bitmap != null) {
            reshape(ivPic, bitmap, reshapeWidth)
            ivPic.setImageBitmap(bitmap)
            Log.d(TAG, "display: Get bitmap from memory, url = ${url}")
            return
        }

        // 本地缓存
        bitmap = localCacheUtil.getBitmap(url)
        if (bitmap != null) {
            reshape(ivPic, bitmap, reshapeWidth)
            ivPic.setImageBitmap(bitmap)
            Log.d(TAG, "display: Get bitmap from local, url = ${url}")
            return
        }

        // 网络缓存
        netCacheUtil.getBitmap(ivPic, url, reshapeWidth)
        Log.d(TAG, "display: Get bitmap from internet, url = ${url}")
    }

    fun reshape(ivPic: ImageView, bitmap: Bitmap, width:Int) {
        if (width == -1) return
        val height = width *bitmap.height / bitmap.width
        ivPic.layoutParams = LinearLayout.LayoutParams(width, height)
    }

    private const val TAG = "BitmapUtil"
}