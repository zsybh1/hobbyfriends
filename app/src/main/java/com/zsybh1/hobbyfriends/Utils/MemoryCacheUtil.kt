package com.zsybh1.hobbyfriends.Utils

import android.graphics.Bitmap
import android.util.LruCache

class MemoryCacheUtil {
    var lruCache: LruCache<String, Bitmap>

    init {
        val maxSize = (Runtime.getRuntime().maxMemory() / 4).toInt()
        lruCache = LruCache(maxSize)

    }

    fun getBitmap(url:String) : Bitmap? {
        val bitmap = lruCache.get(url)
        return bitmap
    }

    fun saveBitmap(url: String, bitmap: Bitmap?) {
        lruCache.put(url, bitmap)
    }
}