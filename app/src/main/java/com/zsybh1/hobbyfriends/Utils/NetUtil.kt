package com.zsybh1.hobbyfriends.Utils

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

object NetUtil {
    fun upload(path: String) {

    }
    fun getRequest(Url: String) : String? {
        try {
            val url = URL(Url)
            val conn= url.openConnection() as HttpURLConnection
            conn.readTimeout = 5000
            conn.requestMethod = "GET"

            val response = conn.responseCode
            if (response == HttpURLConnection.HTTP_OK) {
                val input = conn.inputStream
                return getStringFromStream(input)
            }
            else {
                return response.toString()
            }
        }catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun postRequest(Url: String, json: String) : String? {
        try {
            val url = URL(Url)
            val conn = url.openConnection() as HttpURLConnection
            conn.readTimeout = 5000
            conn.requestMethod = "POST"

            conn.setRequestProperty("Content-Type", "application/json")

            val output = conn.outputStream
            output.write(json.toByteArray())

            val response = conn.responseCode
            if (response == HttpURLConnection.HTTP_OK) {
                val input = conn.inputStream
                return getStringFromStream(input)
            }
            else {
                return response.toString()
            }
        }catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun deleteRequest(Url: String, json: String) : String? {
        try {
            val url = URL(Url)
            val conn = url.openConnection() as HttpURLConnection
            conn.readTimeout = 5000
            conn.requestMethod = "DELETE"

            val output = conn.outputStream
            output.write(json.toByteArray())

            val response = conn.responseCode
            if (response == HttpURLConnection.HTTP_OK) {
                val input = conn.inputStream
                return getStringFromStream(input)
            }
            else {
                return response.toString()
            }
        }catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun getStringFromStream(input: InputStream) : String {
        val buffer = StringBuffer()
        val reader = BufferedReader(InputStreamReader(input))
        var str: String? = null
        str = reader.readLine()
        while ( str != null) {
            buffer.append(str)
            str = reader.readLine()
        }
        return buffer.toString()
    }
}