package com.tasha.archimain.ui.splash

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasha.archimain.data.source.remote.response.ConfigurationResponse
import okio.buffer
import okio.source

object GetConfigFromFile {

    fun getdataAsString(fileName: String): String {
        return javaClass.classLoader.getResourceAsStream("api-response/$fileName").source().buffer().readString(Charsets.UTF_8)
    }

    fun getDataAsObject(fileName: String): ConfigurationResponse {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName")
        val configString = inputStream.source().buffer().readString(Charsets.UTF_8)


        return Gson().fromJson(
            configString,
            object: TypeToken<ConfigurationResponse>() {}.type
        )
    }
}