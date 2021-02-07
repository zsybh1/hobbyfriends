package com.zsybh1.hobbyfriends.Utils

import android.util.Log
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

object NetUtil {
    private const val TAG = "NetUtil"

    fun upload(Url: String, path: String) : String? {
        try {
            val prefix = "--"
            val lineEnd = "\r\n"
            val boundary = UUID.randomUUID().toString()
            val charset = "utf-8"

            val url = URL(Url)
            val conn = url.openConnection() as HttpURLConnection
            conn.doInput = true
            conn.doOutput = true
            conn.useCaches = false
            conn.readTimeout = 5000
            conn.requestMethod = "POST"
            conn.setRequestProperty("Connection", "keep-alive")
            conn.setRequestProperty("Charset", charset);
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=$boundary")


            val file = File(path)
            val input = FileInputStream(file)

            val outStream = conn.outputStream
            val output = DataOutputStream(outStream)
            val sb = StringBuffer()
            sb.append(prefix);
            sb.append(boundary);
            sb.append(lineEnd);
            sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + lineEnd);
            sb.append("Content-Type: application/octet-stream; charset=" + charset + lineEnd);
            sb.append(lineEnd);
            output.write(sb.toString().toByteArray())
            val bytes = ByteArray(1024)
            var len = input.read(bytes)
            while (len != -1) {
                output.write(bytes, 0, len)
                len = input.read(bytes)
            }
            input.close()

            output.write(lineEnd.toByteArray())
            output.write((prefix + boundary + prefix + lineEnd).toByteArray())
            output.flush()

            val response = conn.responseCode
            if (response == HttpURLConnection.HTTP_OK) {
                val ins = conn.inputStream
                return getStringFromStream(ins)
            }
            else {
                return response.toString()
            }

        }catch (e:Exception) {
            e.printStackTrace()
            return null
        }
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