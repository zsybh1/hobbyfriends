package com.zsybh1.hobbyfriends.Utils

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimeUtil {
    fun getLDTfromString(time: String) : LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(time, formatter)
    }

    fun getRelativeTimeString(time: LocalDateTime) : String{
        val now = LocalDateTime.now()
        val duration = Duration.between(time, now)
        if (duration.toMinutes() <= 0) {
            return "刚刚"
        }
        else if (duration.toHours() <= 0) {
            return "${duration.toMinutes()}分钟前"
        }
        else if (duration.toDays() <= 0) {
            return "${duration.toHours()}小时前"
        }
        else if (time.year == now.year)
            return time.format(DateTimeFormatter.ofPattern("MM-dd"))
        else
            return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    fun getCountDownTimeString(time: LocalDateTime) : String {
        val now = LocalDateTime.now()
        val duration = Duration.between(now, time)
        if (duration.toMinutes() <= 0) {
            return "1 分钟内"
        }
        else if (duration.toHours() <= 0) {
            return "${duration.toMinutes()} 分钟"
        }
        else if (duration.toDays() <= 0) {
            return "${duration.toHours()} 小时"
        }
        else {
            return "${duration.toDays()} 天"
        }
    }

    fun getQQLikeTimeString(time: LocalDateTime): String{
        val now = LocalDateTime.now()
        if (time.year != now.year ||  now.dayOfYear - time.dayOfYear >= 7) {
            return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }
        else if (now.dayOfYear - time.dayOfYear >= 1) {
            val weekOfDay = arrayOf("星期一", "星期二","星期三","星期四","星期五","星期六","星期日")
            return weekOfDay[time.dayOfWeek.value - 1]
        }
        else return time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}