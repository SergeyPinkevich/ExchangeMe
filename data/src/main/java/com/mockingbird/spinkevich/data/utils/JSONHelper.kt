package com.mockingbird.spinkevich.data.utils

import android.content.Context
import com.google.gson.Gson
import com.mockingbird.spinkevich.data.entity.CountriesInfo
import com.mockingbird.spinkevich.data.mapper.RestDataMapper
import com.mockingbird.spinkevich.domain.entity.Country
import java.io.IOException
import javax.inject.Inject

class JSONHelper @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    fun parse(jsonString: String): List<Country> {
        val info = readJson(jsonString)
        return info!!.list.map { RestDataMapper.convertToDomain(it) }
    }

    private fun readJson(jsonString: String): CountriesInfo? {
        val json: String
        try {
            val inputStream = context.assets?.open(jsonString)
            val size = inputStream?.available()
            val buffer = ByteArray(size!!)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
            return gson.fromJson(json, CountriesInfo::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}