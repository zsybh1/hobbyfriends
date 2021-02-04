package com.zsybh1.hobbyfriends.Utils

import java.security.MessageDigest

object MD5Util {
    fun encode(content:String):String{
        val hash = MessageDigest.getInstance("MD5").digest(content.toByteArray())
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            var str = Integer.toHexString(b.toInt())
            if (b < 0x10) {
                str = "0$str"
            }
            hex.append(str.substring(str.length -2))
        }
        return hex.toString()
    }
}