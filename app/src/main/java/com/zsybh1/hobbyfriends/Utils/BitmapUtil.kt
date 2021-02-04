package com.zsybh1.hobbyfriends.Utils

import android.util.Log
import android.widget.ImageView
import android.widget.Toast

object BitmapUtil {
    private val netCacheUtil = NetCacheUtil()
    val localCacheUtil = LocalCacheUtil()
    val memoryCacheUtil = MemoryCacheUtil()

    fun display(ivPic: ImageView, url: String) {
        //TODO:设置默认图片

        // 内存缓存
        var bitmap = memoryCacheUtil.getBitmap(url)
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap)
            Log.d(TAG, "display: Get bitmap from memory, url = ${url}")
            return
        }

        // 本地缓存
        bitmap = localCacheUtil.getBitmap(url)
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap)
            Log.d(TAG, "display: Get bitmap from local, url = ${url}")
            return
        }

        // 网络缓存
        netCacheUtil.getBitmap(ivPic, url)
        Log.d(TAG, "display: Get bitmap from internet, url = ${url}")
    }

    private const val TAG = "BitmapUtil"
}