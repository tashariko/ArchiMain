package com.tasha.archimain.ui.splash

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okio.buffer
import okio.source

object GetConfigFromFile {

    fun getdataAsString(fileName: String): String {
        return javaClass.classLoader.getResourceAsStream("api-response/$fileName").source().buffer().readString(Charsets.UTF_8)
    }

    fun <T> getDataAsObject(fileName: String): T {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName")
        val configString = inputStream.source().buffer().readString(Charsets.UTF_8)


        return Gson().fromJson<T>(
            configString,
            object : TypeToken<T>() {}.type
        )
    }
}